package com.suchocki.bookfair.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Id
	@Column(name = "username")
	private String username;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "password")
	private String password;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	@Column(name = "email")
	private String email;

	@Column(name = "school")
	private String school;
	
	
	@ManyToMany
	@JoinTable(
			name="user_role",
			joinColumns = @JoinColumn(name="username"),
			inverseJoinColumns = @JoinColumn(name="authority")
			)
	private List<String> stringAuthorities;

	private List<GrantedAuthority> authorities;

	public User() {

	}

	public User(@NotNull(message = "is required") @Size(min = 1, message = "is required") String username,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String password,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String firstName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String lastName,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String email, String school) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.school = school;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<String> getStringAuthorities() {
		return stringAuthorities;
	}

	public void setStringAuthorities(List<String> stringAuthorities) {
		this.stringAuthorities = stringAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
