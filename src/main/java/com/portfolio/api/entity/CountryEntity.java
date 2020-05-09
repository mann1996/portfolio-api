package com.portfolio.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "countries")
public class CountryEntity implements Serializable {

	private static final long serialVersionUID = -11824530821248507L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
