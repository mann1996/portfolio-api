package com.portfolio.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cities")
public class CityEntity implements Serializable {

	private static final long serialVersionUID = -1216071100392797603L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private StateEntity state;

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

	public StateEntity getState() {
		return state;
	}

	public void setState(StateEntity state) {
		this.state = state;
	}
}
