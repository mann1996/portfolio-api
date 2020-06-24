package com.portfolio.api.ui.response;

import java.time.LocalDateTime;

public class CommentRest {
	private String content;
	private LocalDateTime createdAt = LocalDateTime.now();
	private SimpleUserRest createdBy;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SimpleUserRest getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SimpleUserRest createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
