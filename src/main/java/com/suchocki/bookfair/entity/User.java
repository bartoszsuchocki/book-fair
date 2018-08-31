package com.suchocki.bookfair.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.config.Constant;

@Entity
@Table(name = "user")
public class User implements UserDetails {


	@Id
	@Column(name = "username")
	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	private String username;

	@Column(name = "password")
	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	private String password;

	@Column(name = "enabled")
	private int enabled;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "email")
	private String email;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "school")
	private String school;

	@ManyToMany(fetch = FetchType.EAGER) // Zastanowiæ siê jeszcze, czy to na pewno dobre rozwi¹zanie
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "authority"))
	private List<Authority> authorities;
	
	@OneToMany(mappedBy="owner",fetch=FetchType.LAZY)
	private List<Book> possessedBooks;
	
	@OneToMany(mappedBy="purchaser")
	private List<Book> orderedBooks;
	

	public User() {

	}

	public User(String username, String password, String firstName, String lastName, String email, String school) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.school = school;
		this.enabled = 1;
	}

	public User(String username, String password, int enabled, String firstName, String lastName, String email,
			String school) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.school = school;
	}

	public User(String username, String password, int enabled, String firstName, String lastName, String email,
			String school, List<Authority> authorities) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.school = school;
		this.authorities = authorities;
	}

	public void addPossessedBook(Book book) {
		if(possessedBooks==null) {
			possessedBooks = new ArrayList<>();
		}
		possessedBooks.add(book);
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
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	
	@Transient
	@Override
	public boolean isEnabled() {
		return enabled == 1;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", school=" + school + ", authorities="
				+ authorities + "]";
	}

	@Transactional
	public List<Book> getPossessedBooks() {
		System.out.println("User.getPossessedBooks(): Próba");
		if(possessedBooks==null) {
			System.out.println("possessedBooks to null");
		}
		System.out.println("User: getPossessedBooks(): "+ possessedBooks);
		return possessedBooks;
	}

	public void setPossessedBooks(List<Book> possessedBooks) {
		this.possessedBooks = possessedBooks;
	}

	public List<Book> getOrderedBooks() {
		return orderedBooks;
	}

	public void setOrderedBooks(List<Book> orderedBooks) {
		this.orderedBooks = orderedBooks;
	}
	

}
