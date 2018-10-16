package com.suchocki.bookfair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

@Controller
@RequestMapping("/order")
public class OrderController extends AfterAuthenticationManagingController {

	@Autowired
	private BookService bookService;

	@GetMapping("/orderBook/{bookId}")
	public String orderBook(@PathVariable("bookId") int id) { // sprawdziæ, czy dzia³a (int, nie Integer)

		Book orderedBook = bookService.getBook(id);
		if (orderedBook == null) {
			return "book-not-ordered";
		}
		User loggedUser = getAuthenticatedUser();
		loggedUser.addOrderedBook(orderedBook);
		orderedBook.setPurchaser(loggedUser);
		bookService.saveBook(orderedBook);

		return "book-ordered";
	}

}
