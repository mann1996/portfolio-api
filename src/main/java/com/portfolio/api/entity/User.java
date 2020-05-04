package com.portfolio.api.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	private LocalDateTime joinedOn = LocalDateTime.now();

	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	private Profile profileId;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public LocalDateTime getJoinedOn() {
		return joinedOn;
	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_skill", joinColumns = {
//			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
//					@JoinColumn(name = "skill_id", referencedColumnName = "id", nullable = false, updatable = false) })
//	private Set<Skill> skills = new HashSet<>();

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the skills
	 */
//	public Set<Skill> getSkills() {
//		return skills;
//	}
//
//	/**
//	 * @param skills the skills to set
//	 */
//	public void setSkills(Set<Skill> skills) {
//		this.skills = skills;
//	}

	/**
	 * @return the posts
	 */
	public Set<Post> getPosts() {
		return posts;
	}

	public Profile getProfileId() {
		return profileId;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public void setJoinedOn(LocalDateTime joinedOn) {
		this.joinedOn = joinedOn;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public void setProfileId(Profile profileId) {
		this.profileId = profileId;
	}

}
