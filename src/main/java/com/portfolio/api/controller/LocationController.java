package com.portfolio.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.api.dto.CountryDto;
import com.portfolio.api.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	private LocationService locationService;

	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}

	@GetMapping(value = "/countries")
	public ResponseEntity<List<CountryDto>> getCountries() {
		List<CountryDto> dtos = locationService.getCountries();
		return new ResponseEntity<List<CountryDto>>(dtos, HttpStatus.OK);
	}

}
