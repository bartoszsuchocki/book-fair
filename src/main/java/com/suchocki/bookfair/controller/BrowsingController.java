package com.suchocki.bookfair.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.service.BookService;

@Controller
@RequestMapping("/browse")
public class BrowsingController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/booksview")
	public String viewBooks(Model model) {
		model.addAttribute("searchedBook",new Book());
		return "view-books";
	}
	@PostMapping("/processSearchBookForm")
	public String searchForBooks(@ModelAttribute("searchedBook") Book searchedBook, Model model) {
		
		model.addAttribute("queriedBooks",bookService.getMatchingBooks(searchedBook));
		
		return "view-books";
	}
	
	
}
