package com.portfolio.api.repository;

import java.util.List;

import com.portfolio.api.entity.CommentEntity;
import com.portfolio.api.entity.PostEntity;
import com.portfolio.api.entity.ProfileEntity;

public interface SharedRepository {
	public List<ProfileEntity> matchUser(String key);

	public List<PostEntity> findPublicPostsByUser(String userId);

	public List<PostEntity> findPublicPosts();

	public List<PostEntity> matchPost(String key);

	public List<PostEntity> findBySubscription(List<String> users);

	public List<CommentEntity> findByPost(Integer postId);
}
