package com.portfolio.api.dto;

import org.springframework.stereotype.Component;

@Component
public class StateDto {
	private Integer id;
	private String name;
	private CountryDto country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CountryDto getCountry() {
		return country;
	}

	public void setCountry(CountryDto country) {
		this.country = country;
	}
}
