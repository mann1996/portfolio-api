package com.portfolio.api.dto;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class SkillDto {
	private Integer id;
	private String title;
	private String slug;
	private Set<UserDto> users;

	/**
	 * @return the users
	 */
	public Set<UserDto> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<UserDto> users) {
		this.users = users;
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
