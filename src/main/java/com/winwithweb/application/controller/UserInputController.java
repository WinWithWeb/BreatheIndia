package com.winwithweb.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.winwithweb.application.model.EmailDetails;
import com.winwithweb.application.model.User;
import com.winwithweb.application.service.EmailUtility;

@Controller
public class UserInputController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInputController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String setupform(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		model.addAttribute("emaildata", new EmailDetails());
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String processUserform(@ModelAttribute EmailDetails emaildata,Model model) {
		emaildata.toString();
		model.addAttribute("emaildata", new EmailDetails());
		/*LOGGER.info(username+"--"+email+"--");
		if(username=="" || email==""){
			LOGGER.error("Username-"+username+"||"+email+"-");
			return "home";
		}
		else if(username.equals(null) || email.equals(null)){
			LOGGER.error("Error");
			return "home";
		}*/
		EmailUtility.sendEmail(emaildata.getRecepientemailIds());
		return "home";
	}

}
