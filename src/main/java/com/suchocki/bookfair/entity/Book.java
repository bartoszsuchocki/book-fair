package com.suchocki.bookfair.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.suchocki.bookfair.config.Constant;

@Entity
@Table(name = "book")
public class Book {

	@Transient
	private final int MIN_PRICE = 0;

	@Transient
	private final String WRONG_PRICE_MESSAGE = "Price must be min " + MIN_PRICE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "creation_date")
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "book_owner") // book_owner to w bazie id ownera
	private User owner;

	@ManyToOne
	@JoinColumn(name = "purchaser") // purchaser to w bazie id purchasera
	private User purchaser;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "title")
	private String title;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Size(min = 1, message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "author")
	private String author;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Min(value = MIN_PRICE, message = WRONG_PRICE_MESSAGE)
	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "book_condition")
	private String condition;

	@NotNull(message = Constant.REQUIRED_FIELD_MESSAGE)
	@Column(name = "school_type")
	private String schoolType;

	@Column(name = "class")
	private Integer schoolClass;

	public Book() {
		this.creationDate = new Date();
	}

	public Book(String title, String author, double price, String description, String condition, String schoolType,
			int schoolClass) {
		this.creationDate = new Date();
		this.title = title;
		this.author = author;
		this.price = price;
		this.description = description;
		this.condition = condition;
		this.schoolType = schoolType;
		this.schoolClass = schoolClass;
	}

	public Book(String title, String author, double price, String condition, String schoolType) {
		this.creationDate = new Date();
		this.title = title;
		this.author = author;
		this.price = price;
		this.condition = condition;
		this.schoolType = schoolType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(User purchaser) {
		this.purchaser = purchaser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public Integer getSchoolClass() {
		System.out.println("Book: getSchoolClass()");
		return schoolClass;
	}

	public void setSchoolClass(int schoolClass) {
		System.out.println("Book: setSchoolClass(" + schoolClass + ")  ");
		this.schoolClass = schoolClass;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "Book [MIN_PRICE=" + MIN_PRICE + ", WRONG_PRICE_MESSAGE=" + WRONG_PRICE_MESSAGE + ", id=" + id
				+ ", creationDate=" + creationDate + ", owner=" + owner + ", purchaser=" + purchaser + ", title="
				+ title + ", author=" + author + ", price=" + price + ", description=" + description + ", condition="
				+ condition + ", schoolType=" + schoolType + ", schoolClass=" + schoolClass + "]";
	}
	
	
}
