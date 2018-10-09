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
	private int enabled = 1;

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

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Book> possessedBooks;

	@OneToMany(mappedBy = "purchaser")
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
		if (possessedBooks == null) {
			possessedBooks = new ArrayList<>();
		}
		possessedBooks.add(book);
	}

	// thanks to this method, there will no need to refresh user's books from
	// database anytime some book is changed
	public Book editPossessedBookLocally(Book editedBook) { // edits book with same id as editedBook, returns updated
															// book or null if no match
		if (possessedBooks != null) {
			for (Book b : possessedBooks) {
				if (b.getId() == editedBook.getId()) {
					b.setPropertiesFromOtherBook(editedBook);
					System.out.println("Edytowana-owner: " + b.getOwner());
					return b;
				}
			}
		}
		return null;
	}

	// thanks to this method, there will no need to refresh user's books from
	// database anytime some book is deleted
	public void deletePossessedBookLocally(Book book) {
		if (possessedBooks != null) {
			possessedBooks.remove(book);
		}
	}

	public Book findPossessedBook(int id) {
		if (possessedBooks != null) {
			for (Book b : possessedBooks) {
				if (b.getId() == id) {
					return b;
				}
			}
		}
		return null;
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

	public List<Book> getPossessedBooks() {
		System.out.println("User.getPossessedBooks(): Próba");
		if (possessedBooks == null) {
			System.out.println("possessedBooks to null");
		}
		System.out.println("User: getPossessedBooks(): " + possessedBooks);
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		User user = (User) object;
		return this.username.equals(user.getUsername()) && this.firstName.equals(user.getFirstName())
				&& this.lastName.equals(user.getLastName()) && this.email.equals(user.getEmail())
				&& this.school.equals(user.getSchool()) && this.password.equals(user.getPassword());

	}

}
