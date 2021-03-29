package com.cities.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cities.util.CityUtil;
import com.cities.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CityService {

	private final CityUtil cityUtil;

	@Autowired
	public CityService(CityUtil city) {
		this.cityUtil = city;
		loadData();
	}

	public boolean areCitiesConnected(String origin, String destination) {
		if (inValid(origin, destination)) {
			return false;
		}
		Set<String> visited = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(origin);
		visited.add(origin);
		while (!queue.isEmpty()) {
			String currentCity = queue.poll();
			if (currentCity.equals(destination)) {
				return true;
			}
			if (!CollectionUtils.isEmpty(cityUtil.getConnectedCities(currentCity))) {
				for (String city : cityUtil.getConnectedCities(currentCity)) {
					if (!visited.contains(city)) {
						visited.add(city);
						queue.add(city);
					}
				}
			}
		}
		return false;
	}

	protected boolean inValid(String origin, String destination) {
		if (StringUtils.isBlank(origin) || StringUtils.isBlank(origin)) {
			log.info("origin or destination city entered are either null or empty");
			return true;
		}
		if (!cityUtil.isValidCity(origin) && !cityUtil.isValidCity(destination)) {
			log.info("origin or destination city entered are not present in the file");
			return true;
		}
		return false;
	}

	private void loadData() {
		String line = StringUtils.EMPTY;
		try (BufferedReader br = new BufferedReader(new FileReader(Constants.TEXT_FILE_PATH))) {
			while ((line = br.readLine()) != null) {
				String[] cities = line.split(",");
				log.info("{} -> {}", cities[0].trim(), cities[1].trim());
				cityUtil.addConnection(cities[0].trim(), cities[1].trim());
			}
		} catch (IOException e) {
			log.error("Error in reading file:{}", e.getMessage());
		}
	}

}
