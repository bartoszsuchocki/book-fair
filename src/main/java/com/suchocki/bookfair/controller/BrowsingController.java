package com.suchocki.bookfair.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.comparator.BookComparator;
import com.suchocki.bookfair.comparator.BookComparatorProvider;
import com.suchocki.bookfair.comparator.BookSortOption;
import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.propertyEditor.BookComparatorEditor;
import com.suchocki.bookfair.propertyEditor.SchoolEditor;
import com.suchocki.bookfair.searchfilter.BookFilter;
import com.suchocki.bookfair.service.BookService;
import com.suchocki.bookfair.service.UserService;

@Controller
@RequestMapping("/browse")
public class BrowsingController extends AfterAuthenticationManagingController {

	private BookComparator[] bookComparatorOptions;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private List<School> schoolOptionList;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BookComparator.class, new BookComparatorEditor());
		binder.registerCustomEditor(School.class, new SchoolEditor());
	}

	private BookComparator[] getBookComparatorOptions() {
		if (bookComparatorOptions == null) {
			int length = BookSortOption.values().length;
			bookComparatorOptions = new BookComparator[length];
			int counter = 0;
			for (BookSortOption sortOption : BookSortOption.values()) {
				bookComparatorOptions[counter] = BookComparatorProvider.getBookComparator(sortOption);
				counter++;
			}
		}
		return bookComparatorOptions;
	}

	@RequestMapping("/booksview")
	public String viewBooks(Model model) {
		model.addAttribute("bookFilter", new BookFilter());
		model.addAttribute("comparators", getBookComparatorOptions()); // mo�e inny zasi�g atrybutu/ inny rodzaj ?!
		model.addAttribute("schoolOptionList", schoolOptionList);
		return "view-books";
	}

	@RequestMapping("/processSearchBookForm")
	public String searchForBooks(@ModelAttribute("bookFilter") BookFilter bookFilter, Model model) {

		List<Book> queriedBooks;

		if (isUserAuthenticated()) {
			queriedBooks = bookService.getMatchingBooksNotPossessedByUser(bookFilter.getDesiredBook(),
					getAuthenticatedUser().getUsername());
		} else {
			queriedBooks = bookService.getMatchingBooks(bookFilter.getDesiredBook());
		}

		queriedBooks.sort(bookFilter.getBookComparator());

		model.addAttribute("queriedBooks", queriedBooks);
		model.addAttribute("comparators", getBookComparatorOptions());
		model.addAttribute("schoolOptionList", schoolOptionList);

		return "view-books";
	}

	@GetMapping("/user/{username}")
	public String showUser(@PathVariable("username") String username, Model model) {
		User searchedUser = userService.getUser(username);

		if (searchedUser == null) {
			return "user-not-found";
		}

		model.addAttribute("searchedUser", searchedUser);
		return "view-user";
	}

	@GetMapping("/book/{bookId}")
	public String showBook(@PathVariable("bookId") int bookId, Model model) {

		Book searchedBook = bookService.getBook(bookId);
		if (searchedBook == null) {
			return "book-not-found";
		}
		model.addAttribute("searchedBook", searchedBook);

		return "view-book-details";
	}

	@RequestMapping("/getBookPicture/{bookId}")
	public void getBookPicture(@PathVariable("bookId") int bookId, HttpServletResponse response) {

		File pictureFile = new File(
				Constant.PICTURE_SAVE_DESTINATION_PATH + File.separator + bookId + Constant.PICTURE_EXTENSION);

		try {
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(pictureFile));

			response.setBufferSize(Constant.MAX_PICTURE_FILE_SIZE);
			response.setContentType("image/*");

			byte[] bytes = inputStream.readAllBytes();
			inputStream.close();

			response.getOutputStream().write(bytes);
			response.getOutputStream().close();

		} catch (IOException e) {
			logger.warning(e.getMessage());
		}

	}
}
