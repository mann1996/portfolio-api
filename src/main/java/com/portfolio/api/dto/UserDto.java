package com.portfolio.api.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class UserDto {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDateTime joinedOn;
	private ProfileDto profile;

//	private Set<SkillDto> skills;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(LocalDateTime joinedOn) {
		this.joinedOn = joinedOn;
	}

	public ProfileDto getProfile() {
		return profile;
	}

	public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}
}
