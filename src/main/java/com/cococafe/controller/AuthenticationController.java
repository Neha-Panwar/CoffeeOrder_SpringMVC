package com.cococafe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cococafe.service.AuthenticationService;

@Controller
@SessionAttributes("name")
public class AuthenticationController {

	@Autowired
	AuthenticationService service;

	@GetMapping(value="/login")
	public String showCustomLoginPage() {
	
		return "login";
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	
		service.logout(request, response);
		
		return "redirect:/";
	}
	
	@GetMapping(value="/")
	public String showWelcomePage(ModelMap model) {
		model.put("name", service.getLoggedinUserName());
		return "welcome";
	}
	
	
	
}
