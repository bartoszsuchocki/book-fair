package com.suchocki.bookfair.dao;

import java.util.List;

import com.suchocki.bookfair.entity.Book;

public interface BookDAO {
	public Book getBook(int id);

	public List<Book> getAllBooks();

	public void deleteBook(int id);

	public void saveBook(Book book);

	public List<Book> getUserBooks(String username);
	
	public List<Book> getUserOrderedBooks(String username);

	public List<Book> getMatchingBooks(Book criteriaBook);
	
	public List<Book> getMatchingBooksNotPossessedByUser(Book criteriaBook, String username);

	public void deleteBook(Book book);

	public List<Book> getLastAddedBooks(int limit);
}
