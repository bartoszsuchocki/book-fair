package com.suchocki.bookfair.authentication;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private BookService bookService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User authenticatedUser = (User) authentication.getPrincipal();
		authenticatedUser.setPossessedBooks(bookService.getUserBooks(authenticatedUser.getUsername()));
		authenticatedUser.setOrderedBooks(bookService.getUserOrderedBooks(authenticatedUser.getUsername()));

		System.out.println("success-referer: " + request.getHeader("Referer"));
		System.out.println("success-urlBeforeLogin" + request.getParameter("urlBeforeLogin"));
		
		logger.info("Authenticated user: " + authenticatedUser.getUsername());
		
		String beforeLoginUrl = request.getParameter("urlBeforeLogin");
		if(beforeLoginUrl == null) {
			response.sendRedirect(request.getContextPath() + "/myAccount");
		}
		else {
			response.sendRedirect(beforeLoginUrl);
		}
		
		
	}

}
