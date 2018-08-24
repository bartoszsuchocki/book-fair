package com.suchocki.bookfair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/myAccount")
	public String showMyAccount() {
		return "my-account";
	}
	@RequestMapping("/newBook")
	public String addNewBook() {
		return "addBook";
	}
	
}
