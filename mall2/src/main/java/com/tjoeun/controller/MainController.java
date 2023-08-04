package com.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// / <-- localhost:portnumber
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	
	

}
