package com.portfolio.api.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class CityDto implements Serializable {

	private static final long serialVersionUID = -3736830362914702035L;

	private Integer id;
	private String name;
	private StateDto state;

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

	public StateDto getState() {
		return state;
	}

	public void setState(StateDto state) {
		this.state = state;
	}
}
