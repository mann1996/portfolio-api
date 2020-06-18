package com.portfolio.api.service;

import java.util.List;

import com.portfolio.api.dto.PostDto;

public interface PostService {

	public PostDto findPost(Integer id);

	public Integer savePost(PostDto post);

	public List<PostDto> findPublicPostsByUser(String userId);

	public List<PostDto> findPublicPosts();

	public void deletePost(Integer id);

	public Long toggleLike(String userId, Integer postId);

	public Long addView(Integer postId);

	public List<PostDto> matchPost(String key);

	public List<PostDto> findBySubscription(String userId);
}
