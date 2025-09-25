package com.spring.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null,
					 HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testSetup(){
		
		return new ResponseEntity<>("Hello", HttpStatus.CREATED);
	}

}
