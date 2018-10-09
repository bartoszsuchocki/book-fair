package com.suchocki.bookfair.service;

import java.util.List;

import com.suchocki.bookfair.dao.BookWithoutOwnerSavingException;
import com.suchocki.bookfair.entity.Book;


public interface BookService {
	public Book getBook(int id);

	public List<Book> getAllBooks();
	
	public List<Book> getUserBooks(String username); 
	
	public List<Book> getMatchingBooks(Book criteriaBook);
	
	public void deleteBook(int id);

	public void saveBook(Book book) throws BookWithoutOwnerSavingException;

	public void deleteBook(Book book);
	
	
}
