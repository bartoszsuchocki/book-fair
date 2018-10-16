package com.suchocki.bookfair.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.service.BookService;
import com.suchocki.bookfair.service.UserService;

@Controller
public class UserController {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/myAccount")
	public String showMyAccount(Model model) {
		User currentLoggedUser = getAuthenticatedUser();
		currentLoggedUser.setPossessedBooks(bookService.getUserBooks(currentLoggedUser.getUsername()));
		currentLoggedUser.setOrderedBooks(bookService.getUserOrderedBooks(currentLoggedUser.getUsername()));

		return "my-account";
	}

	@RequestMapping("/newBook")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "add-book";
	}

	@PostMapping("/processNewBookForm")
	public String processNewBookForm(@Valid @ModelAttribute("book") Book newBook, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "add-book";
		}

		newBook.setOwner(getAuthenticatedUser());
		bookService.saveBook(newBook);

		return "book-added-confirmation";
	}

	@GetMapping("/editForm")
	public String showEditForm(Model model) {

		model.addAttribute("loggedUser", getAuthenticatedUser());

		return "edit-account-form";
	}

	@PostMapping("/processEditAccountForm")
	public String processEditAccountForm(@Valid @ModelAttribute("loggedUser") User editedUser,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("B³êdy!!!");
			System.out.println(bindingResult.getAllErrors());
			return "edit-account-form";
		}

		updateAuthenticatedUserProperties(editedUser);

		userService.saveUser(editedUser);

		return "my-account";
	}

	private void updateAuthenticatedUserProperties(User updatedUser) {
		User currentLoggedUser = getAuthenticatedUser();
		currentLoggedUser.setFirstName(updatedUser.getFirstName());
		currentLoggedUser.setLastName(updatedUser.getLastName());
		currentLoggedUser.setEmail(updatedUser.getEmail());
		currentLoggedUser.setSchool(updatedUser.getSchool());
	}

	@GetMapping("/editPasswordForm")
	public String showEditPasswordForm() {
		return "edit-password-form";
	}

	@PostMapping("/processEditPasswordForm")
	public String processEditPasswordForm(HttpServletRequest request, Model model) {

		String currentPassword = request.getParameter("currentPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");

		String error = errorFromPasswordEdition(currentPassword, newPassword1, newPassword2);

		if (error != null) {
			model.addAttribute("errorEditPasswordMessage", error);
			return "edit-password-form";
		}

		model.addAttribute("myAccountSuccessMsg", Constant.PASSWORD_EDITED_MSG);
		changeAuthenticatedUserPassword(newPassword1);

		return "my-account";
	}

	private String errorFromPasswordEdition(String currentPassword, String newPassword1, String newPassword2) {
		// returns null if passwords are correct

		if (currentPassword == null || newPassword1 == null || newPassword2 == null || currentPassword.equals("")
				|| newPassword1.equals("") || newPassword2.equals("")) {
			return Constant.EMPTY_FIELDS_MSG;
		}
		System.out.println(currentPassword + " " + newPassword1 + " " + newPassword2);

		if (!(passwordEncoder.matches(currentPassword, getAuthenticatedUser().getPassword()))) {
			return Constant.INCORRECT_CURRENT_PASSWORD_MSG;
		}
		if (!(newPassword1.equals(newPassword2))) {
			return Constant.DIFFERENT_NEW_PASSWORDS_MSG;
		}

		return null;
	}

	private void changeAuthenticatedUserPassword(String newPassword) {
		User currentLoggedUser = getAuthenticatedUser();
		currentLoggedUser.setPassword(passwordEncoder.encode(newPassword));
		userService.saveUser(currentLoggedUser);
	}

	private User getAuthenticatedUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
