package com.portfolio.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.api.dto.CommentDto;
import com.portfolio.api.dto.PostDto;
import com.portfolio.api.dto.ProfileDto;
import com.portfolio.api.dto.UserDto;
import com.portfolio.api.service.PostService;
import com.portfolio.api.service.UserService;
import com.portfolio.api.ui.request.CommentRequest;
import com.portfolio.api.ui.request.EditPostRequest;
import com.portfolio.api.ui.response.CommentRest;
import com.portfolio.api.ui.response.PostRest;
import com.portfolio.api.ui.response.ProfileRest;
import com.portfolio.api.ui.response.SimpleUserRest;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/save")
	public ResponseEntity<Integer> savePost(@RequestBody EditPostRequest post, @RequestParam Integer id,
			Principal principal) {
		try {
			PostDto dto = new PostDto();
			ModelMapper mapper = new ModelMapper();
			dto = mapper.map(post, PostDto.class);
			if (principal != null) {
				ProfileDto createdBy = userService.findProfileByUserId(this.getCurrentUser(principal));
				dto.setCreatedBy(createdBy);
				if (id != null) {
					dto.setId(id);
				}
				Integer returnValue = postService.savePost(dto);
				return new ResponseEntity<Integer>(returnValue, HttpStatus.CREATED);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/view/{id}")
	public ResponseEntity<PostRest> findPost(@PathVariable Integer id, Principal principal) {
		try {
			PostDto dto = new PostDto();
			dto = postService.findPost(id);
			ModelMapper mapper = new ModelMapper();
			PostRest postRest = mapper.map(dto, PostRest.class);
			ProfileRest createdBy = mapper.map(dto.getCreatedBy(), ProfileRest.class);
			postRest.setCreatedBy(createdBy);
			postRest.setIsLiked(false);
			if (principal != null) {
				String email = principal.getName();
				UserDto userDto = userService.findByEmail(email);
				if (dto.getLikedBy().contains(userDto))
					postRest.setIsLiked(true);
			}
			return new ResponseEntity<PostRest>(postRest, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/user/{userId}/public")
	public ResponseEntity<List<PostRest>> findPublicPostsByUser(@PathVariable String userId) {
		try {
			List<PostDto> dtos = postService.findPublicPostsByUser(userId);
			ModelMapper mapper = new ModelMapper();
			List<PostRest> posts = new ArrayList<PostRest>();
			for (PostDto dto : dtos) {
				posts.add(mapper.map(dto, PostRest.class));
			}
			return new ResponseEntity<List<PostRest>>(posts, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<PostRest>> findPublicPosts() {
		try {
			List<PostDto> dtos = postService.findPublicPosts();
			ModelMapper mapper = new ModelMapper();
			List<PostRest> posts = new ArrayList<PostRest>();
			for (PostDto dto : dtos) {
				PostRest post = mapper.map(dto, PostRest.class);
				post.setCreatedBy(mapper.map(dto.getCreatedBy(), ProfileRest.class));
				posts.add(post);

			}
			return new ResponseEntity<List<PostRest>>(posts, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id) {
		this.postService.deletePost(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping(value = "/toggle/like/{postId}")
	public ResponseEntity<Long> toggleLike(@PathVariable Integer postId, Principal principal) {
		try {
			if (principal != null) {
				String userId = this.getCurrentUser(principal);
				Long likes = postService.toggleLike(userId, postId);
				return new ResponseEntity<Long>(likes, HttpStatus.OK);
			}
			return new ResponseEntity<Long>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/add/view/{id}")
	public ResponseEntity<Long> addView(@PathVariable Integer id) {
		try {
			Long views = postService.addView(id);
			return new ResponseEntity<Long>(views, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<PostRest>> searchPost(@RequestParam String key) {
		try {
			List<PostDto> dtos = postService.matchPost(key);
			ModelMapper mapper = new ModelMapper();
			List<PostRest> postRests = new ArrayList<PostRest>();
			for (PostDto postDto : dtos) {
				PostRest rest = mapper.map(postDto, PostRest.class);
				ProfileRest createdBy = mapper.map(postDto.getCreatedBy(), ProfileRest.class);
				rest.setCreatedBy(createdBy);
				postRests.add(rest);
			}
			return new ResponseEntity<List<PostRest>>(postRests, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/subscription")
	public ResponseEntity<List<PostRest>> findBySubscription(Principal principal) {
		try {
			if (principal != null) {
				String userId = this.getCurrentUser(principal);
				List<PostDto> dtos = postService.findBySubscription(userId);
				ModelMapper mapper = new ModelMapper();
				List<PostRest> postRests = new ArrayList<PostRest>();
				for (PostDto postDto : dtos) {
					PostRest rest = mapper.map(postDto, PostRest.class);
					ProfileRest createdBy = mapper.map(postDto.getCreatedBy(), ProfileRest.class);
					rest.setCreatedBy(createdBy);
					postRests.add(rest);
				}
				return new ResponseEntity<List<PostRest>>(postRests, HttpStatus.OK);
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/post/comment")
	public ResponseEntity<CommentRest> postComment(@RequestBody CommentRequest comment, Principal principal) {
		try {
			if (principal != null) {
				String userId = this.getCurrentUser(principal);
				CommentDto dto = postService.postComment(comment, userId);
				ModelMapper mapper = new ModelMapper();
				CommentRest response = mapper.map(dto, CommentRest.class);
				ProfileDto profileDto = userService.findProfileByUserId(userId);
				SimpleUserRest createdBy = mapper.map(profileDto, SimpleUserRest.class);
				response.setCreatedBy(createdBy);
				return new ResponseEntity<CommentRest>(response, HttpStatus.CREATED);
			} else
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/comments/{postId}")
	public ResponseEntity<List<CommentRest>> findCommentsByPost(@PathVariable Integer postId) {
		try {
			List<CommentDto> dtos = postService.getComments(postId);
			List<CommentRest> comments = new ArrayList<CommentRest>();
			ModelMapper mapper = new ModelMapper();
			for (CommentDto commentDto : dtos) {
				CommentRest comment = mapper.map(commentDto, CommentRest.class);
				ProfileDto profileDto = userService.findProfileByUserId(commentDto.getCreatedBy().getPublicId());
				SimpleUserRest createdBy = mapper.map(profileDto, SimpleUserRest.class);
				comment.setCreatedBy(createdBy);
				comments.add(comment);
			}
			return new ResponseEntity<List<CommentRest>>(comments, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	private String getCurrentUser(Principal principal) {
		String email = principal.getName();
		return userService.findByEmail(email).getPublicId();
	}

}
