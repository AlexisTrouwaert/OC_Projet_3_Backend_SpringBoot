package com.ChaTop.ChaTop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChaTop.ChaTop.services.JWTServices;

@RestController
public class LoginController {

	public JWTServices jwtServices;
	
	public LoginController(JWTServices jwtServices) {
		this.jwtServices = jwtServices;
	}
	
	@PostMapping("/login")
	public String getToken(Authentication authentication) {
		String token = jwtServices.generateToken(authentication);
		return token;
	}
}
