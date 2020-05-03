package com.portfolio.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class PostDto {
	private Integer id;
	private String title;
	private String thumbnail;
	private String content;
	private boolean enabled;
	private Long likes;
	private Long views;
	private LocalDateTime timestamp;
	private LocalDateTime updated_at;
	private UserDto author;
	private Set<KeywordDto> keywords;

	/**
	 * @return the keywords
	 */
	public Set<KeywordDto> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(Set<KeywordDto> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the likes
	 */
	public Long getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(Long likes) {
		this.likes = likes;
	}

	/**
	 * @return the views
	 */
	public Long getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(Long views) {
		this.views = views;
	}

	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the updated_at
	 */
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	/**
	 * @param updated_at the updated_at to set
	 */
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * @return the author
	 */
	public UserDto getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(UserDto author) {
		this.author = author;
	}

}
