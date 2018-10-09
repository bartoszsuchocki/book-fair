package com.suchocki.bookfair.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.dao.BookWithoutOwnerSavingException;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

@Controller
@RequestMapping("/bookManagement")
public class BookController {

	// private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private BookService bookService;

	@GetMapping("/editBook/{bookId}")
	public String editBook(@PathVariable("bookId") int bookId, Model model) {

		Book book = getAuthenticatedUser().findPossessedBook(bookId);
		if (book == null) {
			return "book-not-found";
		}

		model.addAttribute("editedBook", book);

		return "edit-book-form";
	}
	
	@PostMapping("/processEditBookForm")
	public String processBookForm(@Valid @ModelAttribute("editedBook") Book editedBook, BindingResult bindingResult,
			Model model) throws BookWithoutOwnerSavingException {

		if (bindingResult.hasErrors()) {
			return "edit-book-form";
		}

		User currentLoggedUser = getAuthenticatedUser();
		editedBook = currentLoggedUser.editPossessedBookLocally(editedBook);

		bookService.saveBook(editedBook);
		
		model.addAttribute("myAccountSuccessMsg", Constant.BOOK_EDITED_MSG);
		
		return "my-account";
	}

	@GetMapping("deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId, Model model) {
		Book book = getAuthenticatedUser().findPossessedBook(bookId);
		if (book == null) {
			return "book-not-found";
		}
		bookService.deleteBook(book);
		getAuthenticatedUser().deletePossessedBookLocally(book);

		model.addAttribute("myAccountSuccessMsg", Constant.BOOK_DELETED_MSG);
		
		return "my-account";
	}

	private User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
