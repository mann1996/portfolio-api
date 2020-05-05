package com.portfolio.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.api.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);

	public UserEntity findByPublicId(String id);

}
