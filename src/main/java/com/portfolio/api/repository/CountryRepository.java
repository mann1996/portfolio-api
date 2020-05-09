package com.portfolio.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.portfolio.api.entity.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {

}
