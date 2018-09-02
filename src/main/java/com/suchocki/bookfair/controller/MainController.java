package com.suchocki.bookfair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/menu")
	public String showEntryPage() {

		return "mainmenu";
	}

	@GetMapping("/info")
	public String displayInfoPage() {
		return "info";
	}

}
