package com.suchocki.bookfair.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.propertyEditor.SchoolEditor;
import com.suchocki.bookfair.service.AuthorityService;
import com.suchocki.bookfair.service.SchoolService;
import com.suchocki.bookfair.service.UserService;

@Controller
public class RegistrationController {

	private Map<String, Authority> enabledUserRoles;
	private List<School> enabledSchoolList;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void loadEnabledRoles() {
		System.out.println("RegistrationController @PostConstruct annotated method");
		enabledUserRoles = authorityService.getAllAuthoritiesMap();
	}

	@PostConstruct
	public void loadEnabledSchoolList() {
		enabledSchoolList = schoolService.getAllSchools();
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		dataBinder.registerCustomEditor(School.class, new SchoolEditor());
	}

	@RequestMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		System.out.println("Adding schoolList to the model");
		model.addAttribute("enabledSchoolList", enabledSchoolList);
		System.out.println("After adding schoolList o the model");
		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		System.out.println("B³êdy w bindingResult: " + bindingResult.getAllErrors());
		System.out.println("hasErrors() w bindingResults: " + bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			return "registration";
		} /*
			 * else if (userExists(user.getUsername())) { model.addAttribute("user", new
			 * User()); model.addAttribute("registrationError", "user exists"); return
			 * "registration"; }
			 */
		System.out.println("User: " + user);
		System.out.println("Has³o typa: " + user.getPassword());
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		// encodedPassword = "{bcrypt}" + encodedPassword;

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
