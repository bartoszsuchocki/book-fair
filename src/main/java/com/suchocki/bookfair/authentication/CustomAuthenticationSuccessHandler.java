package com.suchocki.bookfair.authentication;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	@Autowired
	private BookService bookService;

	private String defaultTargetUrl = "/userFunctions/myAccount";
	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User authenticatedUser = (User) authentication.getPrincipal();
		authenticatedUser.setPossessedBooks(bookService.getUserBooks(authenticatedUser.getUsername()));
		authenticatedUser.setOrderedBooks(bookService.getUserOrderedBooks(authenticatedUser.getUsername()));

		logger.info("Authenticated user: " + authenticatedUser.getUsername());

		setDefaultTargetUrl(defaultTargetUrl);

		super.onAuthenticationSuccess(request, response, authentication);

	}

}
