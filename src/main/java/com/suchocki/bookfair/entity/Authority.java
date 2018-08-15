package com.suchocki.bookfair.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	@Column(name = "authority")
	private String authorityName;

	@Column(name = "username")
	private String username;

	public Authority() {

	}

	public Authority(String authorityName, String username) {
		this.authorityName = authorityName;
		this.username = username;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
