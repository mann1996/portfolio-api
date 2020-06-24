package com.portfolio.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.dto.CommentDto;
import com.portfolio.api.dto.PostDto;
import com.portfolio.api.dto.ProfileDto;
import com.portfolio.api.dto.UserDto;
import com.portfolio.api.entity.CommentEntity;
import com.portfolio.api.entity.PostEntity;
import com.portfolio.api.entity.ProfileEntity;
import com.portfolio.api.entity.UserEntity;
import com.portfolio.api.repository.CommentRepository;
import com.portfolio.api.repository.PostRepository;
import com.portfolio.api.repository.SharedRepository;
import com.portfolio.api.repository.UserRepository;
import com.portfolio.api.ui.request.CommentRequest;

@Service(value = "postService")
@Transactional
public class PostServiceImpl implements PostService {
	private PostRepository postRepository;
	private UserRepository userRepository;
	private CommentRepository commentRepository;
	private SharedRepository sharedRepository;

	public PostServiceImpl(PostRepository postRepository, SharedRepository sharedRepository,
			UserRepository userRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.sharedRepository = sharedRepository;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public Integer savePost(PostDto post) {
		Optional<PostEntity> postEntity = postRepository.findById(post.getId());
		ModelMapper mapper = new ModelMapper();
		if (!postEntity.isEmpty()) {
			postEntity.get().setIsPublic(post.getIsPublic());
			postEntity.get().setTitle(post.getTitle());
			postEntity.get().setContent(post.getContent());
			postEntity.get().setThumbnail(post.getThumbnail());
			postEntity.get().setUpdatedAt(LocalDateTime.now());
			return postEntity.get().getId();
		}
		PostEntity newEntity = mapper.map(post, PostEntity.class);
		ProfileEntity createdBy = mapper.map(post.getCreatedBy(), ProfileEntity.class);
		newEntity.setCreatedBy(createdBy);
		return postRepository.save(newEntity).getId();
	}

	@Override
	public PostDto findPost(Integer id) {
		Optional<PostEntity> postEntity = postRepository.findById(id);
		ModelMapper mapper = new ModelMapper();
		if (!postEntity.isEmpty()) {
			PostDto dto = mapper.map(postEntity.get(), PostDto.class);
			ProfileDto userDto = mapper.map(postEntity.get().getCreatedBy(), ProfileDto.class);
			dto.setCreatedBy(userDto);
			return dto;
		}
		return null;
	}

	@Override
	public List<PostDto> findPublicPostsByUser(String userId) {
		List<PostEntity> entities = sharedRepository.findPublicPostsByUser(userId);
		List<PostDto> dtos = new ArrayList<PostDto>();

		if (!entities.isEmpty()) {
			ModelMapper mapper = new ModelMapper();
			for (PostEntity postEntity : entities) {
				PostDto dto = mapper.map(postEntity, PostDto.class);
				ProfileDto userDto = mapper.map(postEntity.getCreatedBy(), ProfileDto.class);
				dto.setCreatedBy(userDto);
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public List<PostDto> findPublicPosts() {
		List<PostEntity> entities = sharedRepository.findPublicPosts();
		List<PostDto> dtos = new ArrayList<PostDto>();

		if (!entities.isEmpty()) {
			ModelMapper mapper = new ModelMapper();
			for (PostEntity postEntity : entities) {
				PostDto dto = mapper.map(postEntity, PostDto.class);
				ProfileDto userDto = mapper.map(postEntity.getCreatedBy(), ProfileDto.class);
				dto.setCreatedBy(userDto);
				dtos.add(dto);
			}
		}
		return dtos;
	}

	@Override
	public void deletePost(Integer id) {
		Optional<PostEntity> entity = postRepository.findById(id);

		if (!entity.isEmpty()) {
			entity.get().setCreatedBy(null);
			postRepository.delete(entity.get());
		}

	}

	@Override
	public Long toggleLike(String userId, Integer postId) {
		UserEntity userEntity = userRepository.findByPublicId(userId);
		PostEntity postEntity = postRepository.findById(postId).get();
		Set<UserEntity> likedBy = postEntity.getLikedBy();
		if (!likedBy.contains(userEntity)) {
			postEntity.getLikedBy().add(userEntity);
			postEntity.setLikes(postEntity.getLikes() + 1);
			userEntity.getLikedPosts().add(postEntity);
		} else {
			postEntity.getLikedBy().remove(userEntity);
			postEntity.setLikes(postEntity.getLikes() - 1);
			userEntity.getLikedPosts().remove(postEntity);
		}

		return postEntity.getLikes();

	}

	@Override
	public Long addView(Integer postId) {
		PostEntity postEntity = postRepository.findById(postId).get();
		postEntity.setViews(postEntity.getViews() + 1);
		return postEntity.getViews();
	}

	@Override
	public List<PostDto> matchPost(String key) {
		List<PostEntity> entities = sharedRepository.matchPost(key);
		List<PostDto> dtos = new ArrayList<PostDto>();
		ModelMapper mapper = new ModelMapper();
		for (PostEntity postEntity : entities) {
			PostDto dto = mapper.map(postEntity, PostDto.class);
			ProfileDto createdBy = mapper.map(postEntity.getCreatedBy(), ProfileDto.class);
			dto.setCreatedBy(createdBy);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<PostDto> findBySubscription(String userId) {
		UserEntity entity = userRepository.findByPublicId(userId);
		Set<UserEntity> following = entity.getFollowing();
		List<String> userIds = new ArrayList<String>();
		for (UserEntity userEntity : following) {
			userIds.add(userEntity.getPublicId());
		}
		List<PostEntity> postEntities = sharedRepository.findBySubscription(userIds);
		List<PostDto> dtos = new ArrayList<PostDto>();
		ModelMapper mapper = new ModelMapper();
		for (PostEntity postEntity : postEntities) {
			PostDto dto = mapper.map(postEntity, PostDto.class);
			ProfileDto createdBy = mapper.map(postEntity.getCreatedBy(), ProfileDto.class);
			dto.setCreatedBy(createdBy);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public CommentDto postComment(CommentRequest comment, String userId) {
		PostEntity postEntity = postRepository.findById(comment.getPostId()).get();
		UserEntity user = userRepository.findByPublicId(userId);
		ModelMapper mapper = new ModelMapper();
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setContent(comment.getContent());
		commentEntity.setPost(postEntity);
		commentEntity.setCreatedBy(user);
		commentRepository.save(commentEntity);
		CommentDto dto = mapper.map(commentEntity, CommentDto.class);
		UserDto createdBy = mapper.map(user, UserDto.class);
		dto.setCreatedBy(createdBy);
		return dto;
	}

	@Override
	public List<CommentDto> getComments(Integer postId) {
		List<CommentEntity> comments = sharedRepository.findByPost(postId);
		ModelMapper mapper = new ModelMapper();
		List<CommentDto> dtos = new ArrayList<CommentDto>();
		for (CommentEntity commentEntity : comments) {
			CommentDto dto = mapper.map(commentEntity, CommentDto.class);
			dto.setPost(this.findPost(postId));
			UserDto createdBy = mapper.map(commentEntity.getCreatedBy(), UserDto.class);
			dto.setCreatedBy(createdBy);
			dtos.add(dto);
		}
		return dtos;
	}
}
