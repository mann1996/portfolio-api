package com.portfolio.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.api.dto.UserDto;
import com.portfolio.api.service.UserService;
import com.portfolio.api.ui.request.UserRequestDetails;
import com.portfolio.api.ui.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserRest> getUser(@PathVariable String id) {
		try {
			UserDto user = this.userService.findByPublicId(id);
			UserRest returnValue = new UserRest();
			BeanUtils.copyProperties(user, returnValue);
			return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/create")
	public ResponseEntity<UserRest> createUser(@RequestBody UserRequestDetails user) {
		try {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			UserDto createdUser = this.userService.createUser(userDto);
			UserRest returnValue = new UserRest();
			BeanUtils.copyProperties(createdUser, returnValue);
			return new ResponseEntity<UserRest>(returnValue, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}
}
