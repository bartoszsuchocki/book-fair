package com.suchocki.bookfair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.BookDAO;
import com.suchocki.bookfair.dao.BookWithoutOwnerSavingException;
import com.suchocki.bookfair.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;

	@Override
	@Transactional
	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}

	@Override
	@Transactional
	public List<Book> getUserBooks(String username) {
		return bookDAO.getUserBooks(username);
	}

	@Override
	@Transactional
	public List<Book> getUserOrderedBooks(String username) {
		return bookDAO.getUserOrderedBooks(username);
	}

	@Override
	@Transactional
	public List<Book> getMatchingBooks(Book criteriaBook) {
		return bookDAO.getMatchingBooks(criteriaBook);
	}

	@Override
	@Transactional
	public List<Book> getMatchingBooksNotPossessedByUser(Book criteriaBook, String username) {
		return bookDAO.getMatchingBooksNotPossessedByUser(criteriaBook, username);
	}

	@Override
	@Transactional
	public void deleteBook(int id) {
		bookDAO.deleteBook(id);
	}

	@Override
	@Transactional
	public void deleteBook(Book book) {
		bookDAO.deleteBook(book);
	}

	@Override
	@Transactional
	public void saveBook(Book book) {
		bookDAO.saveBook(book);
	}

}
