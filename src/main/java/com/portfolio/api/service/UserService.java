package com.portfolio.api.service;

import javax.json.JsonPatch;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.portfolio.api.dto.ProfileDto;
import com.portfolio.api.dto.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto user) throws IllegalArgumentException;

	public UserDto findByEmail(String email) throws UsernameNotFoundException;

	public UserDto findByPublicId(String id) throws UsernameNotFoundException;

	public ProfileDto findProfileByUserId(String userId) throws UsernameNotFoundException;

	public void updateProfile(JsonPatch patchDocument, String userId);

	public void addFollower(String userId, String followerId);
}
