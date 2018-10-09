package com.suchocki.bookfair.test.daotest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.suchocki.bookfair.dao.BookDAO;
import com.suchocki.bookfair.dao.BookWithoutOwnerSavingException;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.test.config.TestConfig;

@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDAOTest {

	@Autowired
	private BookDAO bookDAO;

	private static List<Book> sampleBooks;
	
	//To wszystko nie zadzia³a, bo trzeba najpierw zapisaæ ownera!
	
	@BeforeClass
	public static void initializeSampleBooks() {
		sampleBooks = new ArrayList<>();
		sampleBooks.add(new Book("book1", "author1", 13.50, null, "new", "highSchool", 1, "math"));
		sampleBooks.get(0).setOwner(new User("user1", "user1pswd", "Adam", "Kowal", "user1@gmail.com", "Kochanowski"));
		sampleBooks.add(new Book("book2", "author2", 21, "great book", "new", "highSchool", 1, "polish"));
		sampleBooks.add(new Book("book3", "author3", 10, null, "secondhand", "secondarySchool", 1, "french"));
	}

	@Before
	public void printBeforeTest() {
		System.out.println("Before. Teraz w bazie: " + bookDAO.getAllBooks());
	}

	@After
	public void printCurrentStoredBooks() {
		System.out.println("After. Teraz w bazie: " + bookDAO.getAllBooks());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void saveBookTest() throws BookWithoutOwnerSavingException {
		System.out.println("SaveBookTest!");
		Book book = sampleBooks.get(0);

		bookDAO.saveBook(book);
		List<Book> storedBooks = bookDAO.getAllBooks();

		assertNotNull(storedBooks);
		assertTrue(storedBooks.size() == 1);
		assertTrue(storedBooks.get(0).equals(book));
		System.out.println("After SaveBookTest");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void deleteBookTest() throws BookWithoutOwnerSavingException {
		System.out.println("DeleteBookTest");
		for (Book b : sampleBooks) {
			bookDAO.saveBook(b);
		}
		Book bookToDelete = sampleBooks.get(0);
		bookDAO.deleteBook(bookToDelete.getId());

		List<Book> storedBooks = bookDAO.getAllBooks();
		Book shouldBeNullBook = bookDAO.getBook(bookToDelete.getId());

		assertTrue(storedBooks.size() == (sampleBooks.size() - 1));
		assertNull(shouldBeNullBook);
		System.out.println("After DeleteBookTest");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void getBookTest() throws BookWithoutOwnerSavingException {
		System.out.println("GetBookTest");
		Book bookToSave = sampleBooks.get(0);

		bookDAO.saveBook(bookToSave);
		Book storedBook = bookDAO.getBook(bookToSave.getId());

		assertNotNull(storedBook);
		assertTrue(bookToSave.equals(storedBook));
		System.out.println("After GetBookTest");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testOfHibernate() {
		System.out.println("testOfHibernate");
		List<Book> books = bookDAO.getAllBooks();
		assertNull(books);
		System.out.println("After testOfHibernate");
	}

}
