package com.portfolio.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class PostDto implements Serializable {
	private static final long serialVersionUID = 733271136757522976L;
	private Integer id;
	private String title;
	private String thumbnail = "";
	private String content;
	private boolean isPublic;
	private Long likes = 0L;
	private Long views = 0L;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	private ProfileDto createdBy;
	private Set<UserDto> likedBy = new HashSet<UserDto>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ProfileDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ProfileDto createdBy) {
		this.createdBy = createdBy;
	}

	public Set<UserDto> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<UserDto> likedBy) {
		this.likedBy = likedBy;
	}

}
