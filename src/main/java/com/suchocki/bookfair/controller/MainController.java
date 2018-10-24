package com.suchocki.bookfair.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.suchocki.bookfair.config.Constant;

@Controller
public class MainController {

	@GetMapping("/menu")
	public String showEntryPage() {
		Map<String, String> schoolTypes = Constant.SCHOOL_TYPES;
		System.out.println(schoolTypes);
		return "mainmenu";
	}

	@GetMapping("/info")
	public String displayInfoPage() {
		return "info";
	}

}
