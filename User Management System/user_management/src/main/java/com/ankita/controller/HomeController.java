package com.ankita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ankita.model.UserDetails;
import com.ankita.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(HttpSession session, Model model) {
	    // Retrieve the message from the session
	    String message = (String) session.getAttribute("msg");
	    if (message != null) {
	        model.addAttribute("msg", message); // Add it to the model
	        session.removeAttribute("msg"); // Clear it from the session
	    }
	    return "register";
	}

	
	@PostMapping("/createUser")
	public String createuser(@ModelAttribute UserDetails user, HttpSession session) {
		
//		System.out.println(user);
		
		boolean f = userService.checkEmail(user.getEmail());
		
		if(f) {
			
			session.setAttribute("msg", "Email id already exists");
		}
		
		else {			

			UserDetails userDetails = userService.createUser(user);
			
			if(userDetails!=null) {
				
				session.setAttribute("msg", "Registered Successfully");
				
			}
			
			else{
				System.out.println("Something went wrong!!!");
			}
			
		}
		
		return "redirect:/register";
	}
}
