package com.portfolio.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.json.JsonPatch;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.api.dto.ProfileDto;
import com.portfolio.api.dto.UserDto;
import com.portfolio.api.service.UserService;
import com.portfolio.api.ui.request.UserRequestDetails;
import com.portfolio.api.ui.response.EmailValidationRest;
import com.portfolio.api.ui.response.ProfileRest;
import com.portfolio.api.ui.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/show/{id}")
	public ResponseEntity<UserRest> getUser(@PathVariable String id) {
		try {
			UserDto user = this.userService.findByPublicId(id);
			UserRest returnValue = new UserRest();
			BeanUtils.copyProperties(user, returnValue);
			return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registration Failed");
		}

	}

	@PostMapping(value = "/validate")
	public ResponseEntity<EmailValidationRest> existByEmail(@RequestBody EmailValidationRest email) {
		try {
			this.userService.findByEmail(email.getEmail());
			EmailValidationRest response = new EmailValidationRest(true);
			response.setEmail(email.getEmail());
			return new ResponseEntity<EmailValidationRest>(response, HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			EmailValidationRest response = new EmailValidationRest(false);
			response.setEmail(email.getEmail());
			return new ResponseEntity<EmailValidationRest>(response, HttpStatus.OK);
		}

	}

	@GetMapping(value = "/profile/{userId}")
	public ResponseEntity<ProfileRest> getProfileByUserId(@PathVariable String userId, Principal principal) {
		try {
			ProfileDto dto = userService.findProfileByUserId(userId);
			ModelMapper mapper = new ModelMapper();
			ProfileRest response = mapper.map(dto, ProfileRest.class);
			Set<UserDto> followers = userService.getFollowers(userId);
			Set<UserDto> following = userService.getFollowing(userId);
			response.setFollowers(followers.size());
			response.setFollowing(following.size());
			if (principal != null) {
				UserDto userDto = userService.findByEmail(principal.getName());

				if (followers.contains(userDto)) {
					response.setFollowingStatus(true);
				} else {
					response.setFollowingStatus(false);
				}
				if (userId.equals(userDto.getPublicId())) {
					response.setValid(true);
				} else {
					response.setValid(false);
				}
			}
			return new ResponseEntity<ProfileRest>(response, HttpStatus.OK);
		} catch (UsernameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "404 Not Found");
		}
	}

	@PatchMapping(path = "/update/profile/{id}", consumes = "application/json-patch+json")
	public ResponseEntity<ProfileRest> updateProfile(@PathVariable String id, @RequestBody JsonPatch patch,
			Principal principal) {
		try {
			if (principal != null) {
				UserDto userDto = userService.findByEmail(principal.getName());
				if (id.equals(userDto.getPublicId())) {
					ProfileDto dto = userService.updateProfile(patch, id);
					ModelMapper mapper = new ModelMapper();
					ProfileRest rest = mapper.map(dto, ProfileRest.class);
					rest.setValid(true);
					return new ResponseEntity<ProfileRest>(rest, HttpStatus.OK);
				}
			}
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/add/follower/{userId}")
	public ResponseEntity<String> addFollower(@PathVariable String userId, Principal principal) {
		try {
			if (principal != null) {
				UserDto userDto = userService.findByEmail(principal.getName());
				userService.addFollower(userId, userDto.getPublicId());
				return new ResponseEntity<String>(HttpStatus.OK);

			}
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/search")
	public ResponseEntity<List<ProfileRest>> searchUser(@RequestParam String key, Principal principal) {
		List<ProfileDto> dtos = userService.matchUser(key);
		ModelMapper mapper = new ModelMapper();
		List<ProfileRest> list = new ArrayList<ProfileRest>();
		for (ProfileDto dto : dtos) {
			ProfileRest profileRest = mapper.map(dto, ProfileRest.class);
			if (principal != null) {
				UserDto userDto = userService.findByEmail(principal.getName());
				if (dto.getUser().getFollowers().contains(userDto)) {
					profileRest.setFollowingStatus(true);
				}
			}
			list.add(profileRest);
		}
		return new ResponseEntity<List<ProfileRest>>(list, HttpStatus.OK);
	}

}
