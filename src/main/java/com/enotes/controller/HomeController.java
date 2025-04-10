package com.enotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enotes.entity.User;
import com.enotes.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {

		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		
		// Name validation - only alphabets and spaces allowed
		if (!name.matches("^[A-Za-z ]+$")) {
		    session.setAttribute("msg", "Invalid name. Only alphabets and spaces are allowed.");
		    return "redirect:/register";
		}

	    // Basic pattern check for @, ., and at least one uppercase letter
	    if (!email.contains("@") || !email.contains(".") || !email.matches(".*[a-z].*")) {
	        session.setAttribute("msg", "Invalid email format. Must contain '@' and '.'");
	        return "redirect:/register";
	    }
	    
	    // Password validation
	    if (password.length() <= 6 ||
	        !password.matches(".*[A-Z].*") ||       // At least one uppercase letter
	        !password.matches(".*[0-9].*") ||       // At least one digit
	        !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) { // At least one symbol
	        session.setAttribute("msg", "Invalid password. Must be >6 characters, include 1 uppercase, 1 digit, and 1 special symbol.");
	        return "redirect:/register";
	    }

		
		boolean f = userService.existEmailCheck(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email already exist");
		} else {
			User saveUser = userService.saveUser(user);
			if (saveUser != null) {
				session.setAttribute("msg", "Register success");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

//		return "redirect:/register";
		return "login";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	

}