package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.PersonForm;
import com.example.demo.model.Person;

@Controller
public class ManagementController {

	private static List<Person> persons = new ArrayList<Person>();

	static {
		persons.add(new Person("Bill", "Gates"));
		persons.add(new Person("Steve", "Jobs"));
	}

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
	
	@RequestMapping(value = {"/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/admin/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		model.addAttribute("persons", persons);

		return "/admin/personList";
	}

	@RequestMapping(value = { "/admin/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "/admin/addPerson";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();

		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);

			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}
	
	@GetMapping("/error/403")
    public String error403() {
        return "/error/403";
    }

}
