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
import com.suchocki.bookfair.dao.UserDAO;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.test.config.TestConfig;

@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDAOTest {

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private UserDAO userDAO;

	private static List<Book> sampleBooks;
	private static User booksOwner;

	@BeforeClass
	public static void initializeSampleBooksAndBooksOwner() {

		booksOwner = new User("owner", "ownerpswd", "Adam", "Kowal", "owner@gmail.com", new School("Kochanowski")); // to
																													// mo¿e
																													// teraz
																													// nie
																													// dzia³aæ,
																													// mo¿e
																													// trzeba
																													// bêdzie
																													// zapisaæ
																													// szko³ê
																													// w
																													// bamzie

		System.out.println("BeforeClass: booksOwner: " + booksOwner);

		sampleBooks = new ArrayList<>();
		sampleBooks.add(new Book("book1", "author1", 13.50, null, "new", "highSchool", 1, "math"));
		sampleBooks.add(new Book("book2", "author2", 21, "great book", "new", "highSchool", 1, "polish"));
		sampleBooks.add(new Book("book3", "author3", 10, null, "secondhand", "secondarySchool", 1, "french"));

		sampleBooks.get(0).setOwner(booksOwner);
		sampleBooks.get(1).setOwner(booksOwner);
		sampleBooks.get(2).setOwner(booksOwner);

	}

	@Transactional
	@Before
	public void prepareDataBeforeTest() throws BookWithoutOwnerSavingException {
		userDAO.saveUser(booksOwner);
		for (Book b : sampleBooks) {
			bookDAO.saveBook(b);
		}

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
		userDAO.saveUser(booksOwner); // must be saved, because Book must have an owner

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

		userDAO.saveUser(booksOwner);

		Book book = new Book("book1", "author1", 13.50, null, "new", "highSchool", 1, "math");
		book.setOwner(booksOwner);
		bookDAO.saveBook(book);
		bookDAO.deleteBook(book);

		Book shouldBeNullBook = bookDAO.getBook(book.getId());
		assertNull(shouldBeNullBook);

		System.out.println("After DeleteBookTest");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void getBookTest() throws BookWithoutOwnerSavingException {
		System.out.println("GetBookTest");
		userDAO.saveUser(booksOwner);

		Book bookToSave = sampleBooks.get(0);

		bookDAO.saveBook(bookToSave);
		Book storedBook = bookDAO.getBook(bookToSave.getId());

		assertNotNull(storedBook);
		assertTrue(bookToSave.equals(storedBook));
		System.out.println("After GetBookTest");
	}
//
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void testOfHibernate() {
//		System.out.println("testOfHibernate");
//		List<Book> books = bookDAO.getAllBooks();
//		assertNull(books);
//		System.out.println("After testOfHibernate");
//	}

}
