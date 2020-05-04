package com.portfolio.api.dto;

import org.springframework.stereotype.Component;

@Component
public class ProfileDto {
	private Integer id;
	private String jobTitle;
	private String company;
	private String website;
	private String customSectionTitle;
	private String customSectionContent;
	private String instagram;
	private String facebook;
	private String twitter;
	private String youtube;
	private String telegram;
	private String soundcloud;
	private String flickr;
	private CityDto location;

	public CityDto getLocation() {
		return location;
	}

	public void setLocation(CityDto location) {
		this.location = location;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTelegram() {
		return telegram;
	}

	public void setTelegram(String telegram) {
		this.telegram = telegram;
	}

	public String getSoundcloud() {
		return soundcloud;
	}

	public void setSoundcloud(String soundcloud) {
		this.soundcloud = soundcloud;
	}

	public String getFlickr() {
		return flickr;
	}

	public void setFlickr(String flickr) {
		this.flickr = flickr;
	}
}