package com.portfolio.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="post")
@Table(name = "posts")
public class PostEntity implements Serializable {

	private static final long serialVersionUID = -5936426914447550542L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String thumbnail = "";
	private String content;
	@Column(name = "public")
	private boolean isPublic = true;
	private Long likes = 0L;
	private Long views = 0L;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt = LocalDateTime.now();
	@ManyToOne(optional = false)
	@JoinColumn(name = "createdBy")
	private ProfileEntity createdBy;
	@ManyToMany(mappedBy = "likedPosts")
	private Set<UserEntity> likedBy = new HashSet<UserEntity>();

	public Set<UserEntity> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<UserEntity> likedBy) {
		this.likedBy = likedBy;
	}

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

	public ProfileEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ProfileEntity createdBy) {
		this.createdBy = createdBy;
	}

}
