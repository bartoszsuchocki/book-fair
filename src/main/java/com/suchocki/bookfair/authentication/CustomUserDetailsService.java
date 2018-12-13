package com.suchocki.bookfair.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUser(username);

		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found!");
		}
		return user;
	}
	

}
