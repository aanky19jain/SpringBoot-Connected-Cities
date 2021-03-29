package com.cities.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CityUtil {

	private Map<String, List<String>> map = new HashMap<>();

	public List<String> getConnectedCities(String name) {
		return map.get(name);
	}

	public boolean isValidCity(String city) {
		return map.containsKey(city);
	}

	public void addConnection(String origin, String destination) {
		if (!map.containsKey(origin)) {
			addCity(origin);
		}
		if (!map.containsKey(destination)) {
			addCity(destination);
		}
		map.get(origin).add(destination);
		map.get(destination).add(origin);
	}

	public void addCity(String name) {
		map.put(name, new ArrayList<>());
	}

	public Map<String, List<String>> getCityMap() {
		return map;
	}

}
