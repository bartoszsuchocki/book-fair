package com.suchocki.bookfair.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.suchocki.bookfair.entity.Book;

public interface BookService {
	public Book getBook(int id);

	public List<Book> getAllBooks();

	public List<Book> getUserBooks(String username);

	public List<Book> getUserOrderedBooks(String username);

	public List<Book> getMatchingBooks(Book criteriaBook);

	public List<Book> getMatchingBooksNotPossessedByUser(Book criteriaBook, String username);

	public List<Book> getLastAddedBooks(int limit);

	public void deleteBook(int id);

	public void saveBook(Book book);

	public void deleteBook(Book book);

	public String saveBookPicture(MultipartFile pictureFile, String pictureName); // returns message if failed and ok
																					// status if saved (messages and
																					// status definedin Constant class)

}
