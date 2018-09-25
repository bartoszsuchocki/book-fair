package com.suchocki.bookfair.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;

@Controller
public class BookController {

	@GetMapping("/editBook/{bookId}")
	public String editBook(@PathVariable("bookId") int bookId, Model model) {

		Book book = getAuthenticatedUser().findPossessedBook(bookId);
		if (book == null) {
			return "book-not-found";
		}

		model.addAttribute("editedBook", book);

		return "edit-book-form";
	}

	private User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
