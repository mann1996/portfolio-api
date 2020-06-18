package com.portfolio.api.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserDto implements Serializable {

	private static final long serialVersionUID = 2868739908925042014L;

	private Integer id = 0;
	private String publicId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private Set<UserDto> followers = new HashSet<UserDto>();
	private Set<UserDto> following = new HashSet<UserDto>();
	private Set<PostDto> likedPosts = new HashSet<PostDto>();

	public Set<UserDto> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserDto> followers) {
		this.followers = followers;
	}

	public Set<UserDto> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserDto> following) {
		this.following = following;
	}

	public String getEmail() {
		return email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	// private Set<SkillDto> skills;
	public Integer getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public Set<PostDto> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(Set<PostDto> likedPosts) {
		this.likedPosts = likedPosts;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.id == ((UserDto) obj).id)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
