package com.winwithweb.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.winwithweb.application.model.User;
import com.winwithweb.application.service.EmailUtility;

@Controller
public class UserInputController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String setupform(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		User user = new User();
		model.addAttribute("userdetails", user);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	/*
	 * public String processUserform(@ModelAttribute("userdetails") User user,
	 * BindingResult result, SessionStatus status){
	 */
	public String processUserform(@RequestParam String username, @RequestParam String email) {
		// boolean error = false;

		/*
		 * if(user.getUserName().isEmpty()){ result.rejectValue("username",
		 * "error.username"); error = true; }
		 * 
		 * if(user.getEmail().isEmpty()){ result.rejectValue("email",
		 * "error.email"); error = true; }
		 * 
		 * if(user.getPassword().isEmpty()){ result.rejectValue("password",
		 * "error.password"); error = true; }
		 * 
		 * if(error) { return "login"; }
		 */
		System.out.println(username+"--"+email+"--");
		if(username=="" || email==""){
			System.out.println("Error");
		}
		else if(username.equals(null) || email.equals(null)){
			System.out.println("Error");
		}
		
		
		
		EmailUtility.sendEmail(email);
		
		return "home";
	}

}
