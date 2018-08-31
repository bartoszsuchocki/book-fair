package com.suchocki.bookfair.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

@Controller
public class UserController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/myAccount")
	public String showMyAccount() {
		User currentLoggedUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currentLoggedUser.setPossessedBooks(bookService.getUserBooks(currentLoggedUser.getUsername())); //Zapytaæ kogoœ m¹drzejszego czy to jest ok!
		return "my-account";
	}

	@RequestMapping("/newBook")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "add-book";
	}

	@PostMapping("/processNewBookForm")
	public String processNewBookForm(@Valid @ModelAttribute("book") Book newBook, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "add-book";
		}

		User currentLoggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		newBook.setOwner(currentLoggedUser);
		bookService.saveBook(newBook);

		return "book-added-confirmation";
	}

}
