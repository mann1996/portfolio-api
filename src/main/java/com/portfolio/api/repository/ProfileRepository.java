package com.portfolio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.api.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
	public ProfileEntity findByPublicId(String id);

}
