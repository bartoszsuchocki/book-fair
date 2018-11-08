package com.suchocki.bookfair.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.dao.BookDAO;
import com.suchocki.bookfair.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = Logger.getLogger(getClass().getName());

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
	public List<Book> getLastAddedBooks(int limit) {
		return bookDAO.getLastAddedBooks(limit);
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

	@Override
	public String saveBookPicture(MultipartFile pictureFile, String pictureName) {
		if (!pictureFile.isEmpty()) {
			try {
				byte[] bytes = pictureFile.getBytes();

				File dir = new File(
						Constant.PICTURE_SAVE_DESTINATION_ROOT_PATH + File.separator + Constant.PICTURE_SAVE_DIR_NAME);
				if (!dir.exists()) {
					dir.mkdir();
				}
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + pictureName + Constant.PICTURE_EXTENSION);
				System.out.println("Zapisywanie: " + serverFile.getAbsolutePath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			} catch (IOException e) {
				logger.warning(e.getMessage());
				return Constant.CANNOT_SAVE_PICTURE_MSG;
			}
			return Constant.OK_STATUS;
		} else {
			return Constant.WRONG_PICTURE_MSG;
		}
	}

}
