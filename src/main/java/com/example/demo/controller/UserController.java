package com.example.demo.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.enums.PersonStatus;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = {"/user/info" })
	public String index(Model model) {

		return "/user/info";
	}
	
//	 @PostMapping(path="/addUser") // Map ONLY POST Requests
//	  public @ResponseBody String addNewUser (@RequestParam String name
//	      , @RequestParam String email, @RequestParam String password) {
//
//	    User newUser = new User();
//	    newUser.setUserName(name);
//	    newUser.setEmail(email);
//	    newUser.setPassword(email);
//	    newUser.setStartDate(new Date());
//	    newUser.setStatus(PersonStatus.ACTIVE);
//	    userRepository.save(newUser);
//	    
//	    model.addAttribute("users", userRepository.findAll());
//	    return "Saved";
//	  }
	
	@GetMapping("/user/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "/user/register_form";
	}

//	  @GetMapping(path="/admin/userList")
//	  public @ResponseBody Iterable<User> getAllUsers() {
//	    // This returns a JSON or XML with the users
//	    return userRepository.findAll();
//	    
//		return "/admin/personList";
//	  }
	
	@PostMapping("/user/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    Random ran = new Random();
	    int x = ran.nextInt(10);
	    user.setId(x);
	    user.setStartDate(new Date());
	    user.setStatus(PersonStatus.ACTIVE);
	    userRepository.save(user);
	     
	    return "/user/register_success";
	}
	  
	@GetMapping(path="/admin/userList")
	public String personList(Model model) {

		model.addAttribute("users", userRepository.findAll());

		return "/admin/userList";
	}
}
