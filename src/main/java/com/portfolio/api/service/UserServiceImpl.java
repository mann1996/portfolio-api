package com.portfolio.api.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.dto.UserDto;
import com.portfolio.api.entity.ProfileEntity;
import com.portfolio.api.entity.UserEntity;
import com.portfolio.api.repository.ProfileRepository;
import com.portfolio.api.repository.UserRepository;
import com.portfolio.api.utility.Utils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ProfileRepository profileRepository;
	private Utils utils;
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, Utils utils,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.profileRepository = profileRepository;
		this.utils = utils;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto createUser(UserDto user) throws Exception {
		try {
			UserEntity userEntity = new UserEntity();
			user.setPublicId(utils.generateUUID(20));
			user.setEncryptedPassword(this.passwordEncoder.encode(user.getPassword()));
			BeanUtils.copyProperties(user, userEntity);
			ProfileEntity profileEntity = new ProfileEntity();
			profileEntity.setPublicId(utils.generateUUID(20));
			profileEntity.setFirstName(user.getFirstName());
			if (user.getLastName().length() > 0)
				profileEntity.setLastName(user.getLastName());
			this.userRepository.save(userEntity);
			profileEntity.setUser(userEntity);
			this.profileRepository.save(profileEntity);
			UserDto returnValue = new UserDto();
			BeanUtils.copyProperties(userEntity, returnValue);
			return returnValue;
		} catch (Exception e) {
			throw new Exception("something went wrong");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if (entity == null)
			throw new UsernameNotFoundException(email);
		return new User(entity.getEmail(), entity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto findByEmail(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if (entity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(entity, userDto);
		return userDto;
	}

	@Override
	public UserDto findByPublicId(String id) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByPublicId(id);
		if (entity == null)
			throw new UsernameNotFoundException(id);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(entity, userDto);
		return userDto;
	}

}
