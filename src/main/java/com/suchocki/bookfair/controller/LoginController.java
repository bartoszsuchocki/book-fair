package com.suchocki.bookfair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/showLoginForm")
	public String showLoginPage() {
		return "login";
	}
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
