package com.suchocki.bookfair.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.dao.BookWithoutOwnerSavingException;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

@Controller
@RequestMapping("/bookManagement")
public class BookController extends AfterAuthenticationManagingController {

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

	@RequestMapping("/processEditBookForm")
	public String processBookForm(@Valid @ModelAttribute("editedBook") Book editedBook, BindingResult bindingResult,
			@RequestParam("picture") MultipartFile pictureFile, Model model) throws BookWithoutOwnerSavingException {

		if (bindingResult.hasErrors()) {
			return "edit-book-form";
		}

		User currentLoggedUser = getAuthenticatedUser();
		editedBook = currentLoggedUser.editPossessedBookLocally(editedBook);

		bookService.saveBook(editedBook);

		if (pictureFile != null && !pictureFile.isEmpty()) {
			String saveStatus = bookService.saveBookPicture(pictureFile, String.valueOf(editedBook.getId()));
			if (saveStatus != Constant.OK_STATUS) {
				model.addAttribute("customValidationError", saveStatus);
				return "edit-book-form";
			}
		}
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
}
