package com.suchocki.bookfair.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.BookDAO;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDao;

	@Override
	@Transactional
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Book> getUserBooks(String username) {
		return bookDao.getUserBooks(username);
	}

	@Override
	@Transactional
	public List<Book> getMatchingBooks(Book criteriaBook) {
		return bookDao.getMatchingBooks(criteriaBook);
	}

	@Override
	@Transactional
	public void deleteBook(int id) {
		bookDao.deleteBook(id);
	}

	@Override
	@Transactional
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
	}

	@Override
	@Transactional
	public void saveBook(Book book) {
		bookDao.saveBook(book);
	}

}
