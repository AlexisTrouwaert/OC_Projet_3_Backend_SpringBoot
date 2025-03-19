package com.ChaTop.ChaTop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.services.userService;

@RestController
@RequestMapping("/api/user")
public class userController {
	
	private final userService userService;
	
	public userController(userService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
}
