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

import com.suchocki.bookfair.comparator.BookComparator;
import com.suchocki.bookfair.comparator.BookSortOption;
import com.suchocki.bookfair.config.Constant;

@Entity
@Table(name = "book")
public class Book {

	@Transient
	public static final int MIN_PRICE = 0;

	@Transient
	public static final int MAX_PRICE = 1000;

	@Transient
	public static final String ALL_SCHOOL_TYPE_VALUE = "wszystkie";

	@Transient
	public static final String ALL_OPTIONS_FILTER_VALUE = "wszystkie";

	@Transient
	private final String WRONG_PRICE_MESSAGE = "Price must be min " + MIN_PRICE;

	@Transient
	private BookComparator comparator;

	@Transient
	private BookSortOption compareType;

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
	private Double price;

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

	@NotNull
	@Column(name = "topic")
	private String topic;

	public Book() {
		this.creationDate = new Date();
	}

	public Book(String title, String author, double price, String description, String condition, String schoolType,
			Integer schoolClass, String topic) {
		this.creationDate = new Date();
		this.title = title;
		this.author = author;
		this.price = price;
		this.description = description;
		this.condition = condition;
		this.schoolType = schoolType;
		this.schoolClass = schoolClass;
		this.topic = topic;
	}

	public Book(String title, String author, double price, String condition, String schoolType, String topic) {
		this.creationDate = new Date();
		this.title = title;
		this.author = author;
		this.price = price;
		this.condition = condition;
		this.schoolType = schoolType;
		this.topic = topic;
	}
	
	public void setPropertiesFromOtherBook(Book book) { //note that this does not set id from passed book
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.price = book.getPrice();
		this.description = book.getDescription();
		this.condition = book.getCondition();
		this.schoolType = book.getSchoolType();
		this.schoolClass = book.getSchoolClass();
		this.topic = book.getTopic();
	}
	
	public BookSortOption getCompareType() {
		return compareType;
	}

	public void setCompareType(BookSortOption compareType) {
		this.compareType = compareType;
	}

	public void setComparator(BookComparator comparator) {
		this.comparator = comparator;
	}

	public BookComparator getComparator() {
		return comparator;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public void setSchoolClass(Integer schoolClass) {
		System.out.println("Book: setSchoolClass(" + schoolClass + ")  ");
		this.schoolClass = schoolClass;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	@Override
	public String toString() {
		return "Book [MIN_PRICE=" + MIN_PRICE + ", WRONG_PRICE_MESSAGE=" + WRONG_PRICE_MESSAGE + ", id=" + id
				+ ", creationDate=" + creationDate + ", owner=" + owner + ", purchaser=" + purchaser + ", title="
				+ title + ", author=" + author + ", price=" + price + ", description=" + description + ", condition="
				+ condition + ", schoolType=" + schoolType + ", schoolClass=" + schoolClass + "]";
	}

}
