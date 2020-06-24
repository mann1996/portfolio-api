package com.portfolio.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentDto implements Serializable {

	private static final long serialVersionUID = -8408335154155635829L;

	private Integer id;
	private String content;
	private LocalDateTime createdAt = LocalDateTime.now();
	private UserDto createdBy;
	private PostDto post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}

	public PostDto getPost() {
		return post;
	}

	public void setPost(PostDto post) {
		this.post = post;
	}

}
