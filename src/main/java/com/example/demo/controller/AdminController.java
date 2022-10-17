package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	// Được tiêm vào (inject) từ application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}
	
//	@RequestMapping(value = {"/login" }, method = RequestMethod.GET)
//	public String login(Model model) {
//		return "login";
//	}
	
	@RequestMapping(value = {"/admin" }, method = RequestMethod.GET)
	public String admin(Model model) {
		return "/admin/adminHome";
	}
	
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
