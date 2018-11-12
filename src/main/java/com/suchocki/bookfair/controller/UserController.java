package com.suchocki.bookfair.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.suchocki.bookfair.config.Constant;
import com.suchocki.bookfair.entity.Authority;
import com.suchocki.bookfair.entity.Book;
import com.suchocki.bookfair.entity.School;
import com.suchocki.bookfair.entity.User;
import com.suchocki.bookfair.propertyEditor.SchoolEditor;
import com.suchocki.bookfair.service.BookService;
import com.suchocki.bookfair.service.UserService;

@Controller
@RequestMapping("/userFunctions")
public class UserController extends AfterAuthenticationManagingController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private List<School> schoolOptionList;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		dataBinder.registerCustomEditor(School.class, new SchoolEditor());
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

	@RequestMapping("/processNewBookForm")
	public String processNewBookForm(@Valid @ModelAttribute("book") Book newBook, BindingResult bindingResult,
			@RequestParam("picture") MultipartFile bookPicture, HttpServletRequest request, Model model) {

		if (bindingResult.hasErrors()) {
			return "add-book";
		}

		System.out.println("Cena dodawanej: " + newBook.getPrice());
		System.out.println("Tytu³ dodawanej: " + newBook.getTitle());

		String customValidationError = customBookValidationError(newBook);
		if (customValidationError != Constant.OK_STATUS) {
			model.addAttribute("customValidationError", customValidationError);
			return "add-book";
		}

		newBook.setOwner(getAuthenticatedUser());
		bookService.saveBook(newBook);

		String saveStatus = bookService.saveBookPicture(bookPicture, String.valueOf(newBook.getId()));
		if (saveStatus != Constant.OK_STATUS) {
			model.addAttribute("customValidationError", saveStatus);
			return "add-book";
		}

		return "book-added-confirmation";
	}

	private String customBookValidationError(Book book) {
		if (book.getPrice() < Book.MIN_PRICE || book.getPrice() > Book.MAX_PRICE) {
			return Constant.INCORRECT_PRICE_MESSAGE;
		}
		if (book.getDescription() != null && book.getDescription().length() > Constant.MAX_BOOK_DESCRIPTION_SIZE) {
			return Constant.TOO_LARGE_DESCRIPTION_MESSAGE;
		}
		return Constant.OK_STATUS;
	}

	@GetMapping("/editForm")
	public String showEditForm(Model model) {

		model.addAttribute("loggedUser", getAuthenticatedUser());
		model.addAttribute("schoolOptionList", schoolOptionList);

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
}
