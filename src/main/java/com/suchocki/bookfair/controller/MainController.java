package com.suchocki.bookfair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.service.BookService;

@Controller
public class MainController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/menu")
	public String showEntryPage(Model model) {
		model.addAttribute("lastAddedBooks",bookService.getLastAddedBooks(Constant.MAX_BOOKS_DISPLAYED_IN_MAIN_MENU));
		return "mainmenu";
	}

	@GetMapping("/info")
	public String displayInfoPage() {
		return "info";
	}

}
