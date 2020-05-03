//package com.portfolio.api.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.portfolio.api.entity.Post;
//import com.portfolio.api.service.PostService;
//
//@RestController
//@RequestMapping("/api")
//public class PostController {
//	@Autowired
//	private PostService postService;
//
//	@GetMapping(value = "/posts")
//	public ResponseEntity<List<Post>> getPosts() {
//		ResponseEntity<List<Post>> entity = new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
//		return entity;
//	}
//
//	@PostMapping(value = "/posts/add")
//	public ResponseEntity<Integer> savePost(@RequestBody Post post) {
//		Integer id = postService.savePost(post);
//		return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
//	}
//}
