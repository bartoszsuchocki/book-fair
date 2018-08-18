package com.suchocki.bookfair.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.entity.Authority;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.AuthorityService;
import com.suchocki.bookfair.service.UserService;

@Controller
public class RegistrationController {

	/*
	 * @Autowired private UserDetailsManager userDetailsManager; // mo¿e okazaæ siê
	 * nieprzydatne
	 */
	private Map<String, Authority> enabledUserRoles;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void loadEnabledRoles() {
		System.out.println("RegistrationController @PostConstruct annotated method");
		enabledUserRoles = authorityService.getAllAuthoritiesMap();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", new User());
			model.addAttribute("registrationError", "wrong filled fields");
			return "registration";
		} /*
			 * else if (userExists(user.getUsername())) { model.addAttribute("user", new
			 * User()); model.addAttribute("registrationError", "user exists"); return
			 * "registration"; }
			 */
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		//encodedPassword = "{bcrypt}" + encodedPassword;

		List<Authority> authorities = new ArrayList<>();
		authorities.add(enabledUserRoles.get("ROLE_SELLER"));

		User newUser = new User(user.getUsername(), encodedPassword, user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getSchool());
		newUser.setAuthorities(authorities);
		userService.saveUser(newUser);
		System.out.println("Zarejestrowany u¿ytkownik: " + newUser);

		return "registration-confirmation";
	}

	/*
	 * private boolean userExists(String username) { return
	 * userDetailsManager.userExists(username); }
	 */
}
