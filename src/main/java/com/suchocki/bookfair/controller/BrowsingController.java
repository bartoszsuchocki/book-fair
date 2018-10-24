package com.suchocki.bookfair.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.filter.BookFilter;
import com.suchocki.bookfair.propertyEditor.BookComparatorEditor;
import com.suchocki.bookfair.propertyEditor.SchoolEditor;
import com.suchocki.bookfair.service.BookService;
import com.suchocki.bookfair.service.SchoolService;
import com.suchocki.bookfair.service.UserService;

@Controller
@RequestMapping("/browse")
public class BrowsingController {

	private BookComparator[] bookComparatorOptions;
	private List<School> schoolOptionList;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private SchoolService schoolService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(BookComparator.class, new BookComparatorEditor());
		binder.registerCustomEditor(School.class, new SchoolEditor());
	}

	@PostConstruct
	public void loadSchoolOptionList() {
		schoolOptionList = schoolService.getAllSchools();
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
		model.addAttribute("comparators", getBookComparatorOptions()); // mo¿e inny zasiêg atrybutu/ inny rodzaj ?!
		model.addAttribute("schoolOptionList", schoolOptionList);
		return "view-books";
	}

	@RequestMapping("/processSearchBookForm")
	public String searchForBooks(@ModelAttribute("bookFilter") BookFilter bookFilter, Model model) {

		List<Book> queriedBooks = bookService.getMatchingBooks(bookFilter.getDesiredBook());

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

}
