package com.suchocki.bookfair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class Authority implements GrantedAuthority {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public Authority(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Authority() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public String toString() {
		return "Authority [name=" + name + ", description=" + description + "]";
	}

}
