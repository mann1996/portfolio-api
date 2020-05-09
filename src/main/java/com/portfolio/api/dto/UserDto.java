package com.portfolio.api.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserDto implements Serializable {

	private static final long serialVersionUID = 2868739908925042014L;

	private Integer id;
	private String publicId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;

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
}
