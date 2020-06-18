package com.portfolio.api.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 2821616741118243993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String publicId;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column(name = "password")
	private String encryptedPassword;

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	private Set<PostEntity> posts = new HashSet<>();

	@ManyToMany(mappedBy = "following")
	private Set<UserEntity> followers = new HashSet<UserEntity>();
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_following", joinColumns = @JoinColumn(name = "follower_user_id"), inverseJoinColumns = @JoinColumn(name = "following_user_id"))
	private Set<UserEntity> following = new HashSet<UserEntity>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_like", joinColumns = @JoinColumn(name = "liked_by"), inverseJoinColumns = @JoinColumn(name = "liked_posts"))
	private Set<PostEntity> likedPosts = new HashSet<PostEntity>();

	public Set<PostEntity> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(Set<PostEntity> likedPost) {
		this.likedPosts = likedPost;
	}

	public Set<UserEntity> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserEntity> followers) {
		this.followers = followers;
	}

	public Set<UserEntity> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserEntity> following) {
		this.following = following;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Set<PostEntity> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostEntity> posts) {
		this.posts = posts;
	}

}
