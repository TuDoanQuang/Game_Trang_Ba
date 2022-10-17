package com.example.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.enums.PersonStatus;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = {"/user_info" })
	public String index(Model model) {

		return "/user/info";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "/user/register_form";
	}

	@PostMapping("/process_register")
	public String processRegister(@Valid User user, BindingResult bindingResult) {
	     
	    if (bindingResult.hasErrors()) {       
	        return "/user/register_form";
	    } else {
	    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	 	    user.setPassword(encodedPassword);
	 	    user.setStartDate(new Date());
	 	    user.setStatus(PersonStatus.ACTIVE);
	 	    userRepository.save(user);
	 	     
	 	    return "/user/register_success";
	    }
	}
	  
	@GetMapping(path="/userList")
	public String personList(Model model) {

		model.addAttribute("users", userRepository.findAll());

		return "/admin/userList";
	}
	
//	public void addUser(@RequestParam Map<String, String> body) {
//	      User user = new User(); user.setUsername(body.get("username")); 
//	      user.setPassword(passwordEncoder.encode(body.get("password"))); 
//	      user.setAccountNonLocked(true); userDetailsManager.createUser(user); 
//	   }
	
	@GetMapping("/login") 
	public String login(HttpServletRequest request, HttpSession session) {
		session.setAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		return "login";
	} 
	
	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}
}
