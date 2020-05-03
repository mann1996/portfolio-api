package com.portfolio.api.dto;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class KeywordDto {
	private Integer id;
	private String title;
	private String slug;
	private Set<PostDto> posts;

	/**
	 * @return the posts
	 */
	public Set<PostDto> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<PostDto> posts) {
		this.posts = posts;
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
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

}
