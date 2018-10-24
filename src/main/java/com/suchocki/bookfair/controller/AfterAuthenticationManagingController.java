package com.suchocki.bookfair.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.entity.User;

public class AfterAuthenticationManagingController {// controller whose functionallity enables easier managing after
													// authentication operations such as retrieving current logged user
	protected User getAuthenticatedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof User)) {
			return Constant.ANONYMOUS_USER;
		}
		return (User) principal;
	}

	protected boolean isUserAuthenticated() {
		return !(getAuthenticatedUser().equals(Constant.ANONYMOUS_USER));
	}
}
