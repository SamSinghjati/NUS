package com.luv2shopdev.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2shopdev.ecommerce.dto.UserDto;
import com.luv2shopdev.ecommerce.entity.User;
import com.luv2shopdev.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserDto user) {
		try {
			User userDb = userService.login(user);
			return new ResponseEntity<Object>(userDb, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody UserDto user) {

		try {
			User register = userService.register(user);
			return new ResponseEntity<Object>(register, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

	}

}