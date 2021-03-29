package com.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.service.CityService;
import com.cities.util.Constants;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("connected")
	public String areCitiesConnected(@RequestParam String origin, @RequestParam String destination) {
		boolean connected = cityService.areCitiesConnected(origin, destination);
		return connected ? Constants.YES : Constants.NO;
	}

}
