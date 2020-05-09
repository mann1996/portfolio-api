package com.portfolio.api.service;

import java.util.ArrayList;
import java.util.Set;

import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.api.SpringApplicationContext;
import com.portfolio.api.dto.ProfileDto;
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
	public UserDto createUser(UserDto user) throws IllegalArgumentException {
		try {
			UserEntity userEntity = new UserEntity();
			user.setPublicId(utils.generateUUID(20));
			user.setEncryptedPassword(this.passwordEncoder.encode(user.getPassword()));
			BeanUtils.copyProperties(user, userEntity);
			ProfileEntity profileEntity = new ProfileEntity();
			profileEntity.setPublicId(user.getPublicId());
			profileEntity.setFirstName(user.getFirstName());
			if (user.getLastName().length() > 0)
				profileEntity.setLastName(user.getLastName());
			this.userRepository.save(userEntity);
			profileEntity.setUser(userEntity);
			this.profileRepository.save(profileEntity);
			UserDto returnValue = new UserDto();
			BeanUtils.copyProperties(userEntity, returnValue);
			return returnValue;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid Agrument Passed");
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

	@Override
	public ProfileDto findProfileByUserId(String userId) throws UsernameNotFoundException {
		ProfileEntity profileEntity = profileRepository.findByPublicId(userId);
		if (profileEntity == null)
			throw new UsernameNotFoundException(userId);
		ModelMapper mapper = new ModelMapper();
		ProfileDto dto = mapper.map(profileEntity, ProfileDto.class);
		return dto;

	}

	@Override
	public void updateProfile(JsonPatch patchDocument, String userid) {
		ProfileEntity original = profileRepository.findByPublicId(userid); // 1
		ObjectMapper objectMapper = (ObjectMapper) SpringApplicationContext.getBean("objectMapper");

		// Converts the original user to a JsonStructure
		JsonStructure target = objectMapper.convertValue(original, JsonStructure.class); // 2
		// Applies the patch to the original user
		JsonValue patchedUser = patchDocument.apply(target); // 3

		// Converts the JsonValue to a User instance
		ProfileEntity modifiedEntity = objectMapper.convertValue(patchedUser, ProfileEntity.class); // 4
		modifiedEntity.getUser().setFirstName(modifiedEntity.getFirstName());
		modifiedEntity.getUser().setLastName(modifiedEntity.getLastName());

		// Saves the modified user in the database
		profileRepository.save(modifiedEntity);
	}

	@Override
	public void addFollower(String userId, String followerId) {
		UserEntity user = userRepository.findByPublicId(userId);
		UserEntity follower = userRepository.findByPublicId(followerId);

		Set<UserEntity> followers = user.getFollowers();
		if (!followers.contains(follower)) {
			followers.add(follower);
			follower.getFollowing().add(user);
		} else {
			followers.remove(follower);
			follower.getFollowing().remove(user);
		}

	}

}
