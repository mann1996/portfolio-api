//package com.portfolio.api.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.portfolio.api.entity.Post;
//import com.portfolio.api.repository.PostRepository;
//
//@Service(value = "postService")
//@Transactional
//public class PostServiceImpl implements PostService {
//	private PostRepository postRepository;
//
//	public PostServiceImpl(PostRepository postRepository) {
//		this.postRepository = postRepository;
//	}
//
//	@Override
//	public List<Post> getAllPosts() {
//		List<Post> posts = new ArrayList<>();
//		postRepository.findAll().forEach(posts::add);
//		return posts;
//	}
//
//	@Override
//	public Integer savePost(Post post) {
//		post.getCreatedBy().getPosts().add(post);
//		Integer id = postRepository.save(post).getId();
//		return id;
//	}
//}
