package com.portfolio.api.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.api.dto.CountryDto;
import com.portfolio.api.entity.CountryEntity;
import com.portfolio.api.repository.CityRepository;
import com.portfolio.api.repository.CountryRepository;
import com.portfolio.api.repository.StateRepository;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private CountryRepository countryRepository;
	private StateRepository stateRepository;
	private CityRepository cityRepository;

	public LocationServiceImpl(CountryRepository countryRepository, StateRepository stateRepository,
			CityRepository cityRepository) {
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.cityRepository = cityRepository;
	}

	@Override
	public List<CountryDto> getCountries() {

		Iterable<CountryEntity> entities = countryRepository.findAll();
		List<CountryDto> countryDtos = new ArrayList<CountryDto>();
		ModelMapper mapper = new ModelMapper();
		for (CountryEntity entity : entities) {
			countryDtos.add(mapper.map(entity, CountryDto.class));
		}
		return countryDtos;
	}

}
