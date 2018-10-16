package com.suchocki.bookfair.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/showLoginForm")
	public String showLoginPage(HttpServletRequest request) {
		System.out.println(request.getHeader("Referer"));
		return "login";
	}
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
