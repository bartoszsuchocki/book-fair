package com.suchocki.bookfair.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.suchocki.bookfair.entity.User;

public class AfterAuthenticationManagingController {// controller whose functionallity enables easier managing after
													// authentication operations such as retrieving current logged user
	protected User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
