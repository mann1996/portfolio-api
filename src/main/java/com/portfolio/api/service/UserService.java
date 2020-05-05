package com.portfolio.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.portfolio.api.dto.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto user) throws Exception;

	public UserDto findByEmail(String email) throws UsernameNotFoundException;

	public UserDto findByPublicId(String id) throws UsernameNotFoundException;
}
