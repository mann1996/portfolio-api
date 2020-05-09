package com.portfolio.api.ui.response;

public class EmailValidationRest {
	private String email;
	private boolean emailTaken;

	public EmailValidationRest() {

	}

	public EmailValidationRest(boolean emailTaken) {
		this.emailTaken = emailTaken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailTaken() {
		return emailTaken;
	}

	public void setEmailTaken(boolean emailTaken) {
		this.emailTaken = emailTaken;
	}

}
