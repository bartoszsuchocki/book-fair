package com.suchocki.bookfair.service;

import java.util.List;

import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;

public interface BookService {
	public Book getBook(int id);

	public List<Book> getAllBooks();
	
	public List<Book> getUserBooks(String username); 
	
	public void deleteBook(int id);

	public void saveBook(Book book);
	
}
