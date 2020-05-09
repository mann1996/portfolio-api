package com.portfolio.api.service;

import java.util.List;

import com.portfolio.api.dto.CountryDto;

public interface LocationService {
	public List<CountryDto> getCountries();
}
