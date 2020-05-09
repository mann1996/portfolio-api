package com.portfolio.api.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CountryDto implements Serializable {

	private static final long serialVersionUID = -6888617151093893734L;
	private Integer id;
	private String sortname;
	private String name;
	private Integer phonecode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(Integer phonecode) {
		this.phonecode = phonecode;
	}
}
