package com.portfolio.api.ui.response;

import java.time.LocalDateTime;

public class ProfileRest {
	private String publicId;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String company;
	private String website;
	private String customSectionTitle;
	private String customSectionContent;
	private String instagram;
	private String facebook;
	private String twitter;
	private String youtube;
	private String soundcloud;
	private String github;
	private String linkedin;

	private LocalDateTime joinedOn = LocalDateTime.now();
	private UserRest user;
	private boolean valid = false;
	private boolean followingStatus;

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public LocalDateTime getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(LocalDateTime joinedOn) {
		this.joinedOn = joinedOn;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCustomSectionTitle() {
		return customSectionTitle;
	}

	public void setCustomSectionTitle(String customSectionTitle) {
		this.customSectionTitle = customSectionTitle;
	}

	public String getCustomSectionContent() {
		return customSectionContent;
	}

	public void setCustomSectionContent(String customSectionContent) {
		this.customSectionContent = customSectionContent;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getSoundcloud() {
		return soundcloud;
	}

	public void setSoundcloud(String soundcloud) {
		this.soundcloud = soundcloud;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public UserRest getUser() {
		return user;
	}

	public void setUser(UserRest user) {
		this.user = user;
	}

	public boolean isFollowingStatus() {
		return followingStatus;
	}

	public void setFollowingStatus(boolean followingStatus) {
		this.followingStatus = followingStatus;
	}

}
