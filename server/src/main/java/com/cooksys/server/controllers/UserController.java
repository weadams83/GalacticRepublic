package com.cooksys.server.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cooksys.server.DTOs.UserCreateRequestDTO;
import com.cooksys.server.DTOs.UserResponseDTO;
import com.cooksys.server.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
	private UserService userServ;
	
	@GetMapping("/@{username}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponseDTO getUser(@PathVariable("username") String userName) {
		return userServ.getUser(userName);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDTO postUser(@RequestBody UserCreateRequestDTO userRequest) {
		return userServ.postUser(userRequest);
	}

}
