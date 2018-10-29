package com.suchocki.bookfair.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/showLoginForm")
	public String showLoginPage(HttpServletRequest request, Model model) {
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, null);
		if(savedRequest!=null) {
			model.addAttribute("redirectionPerformed", true);
		}
		return "login";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}

}
