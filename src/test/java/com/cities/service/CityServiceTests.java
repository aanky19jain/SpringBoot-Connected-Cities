package com.cities.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.cities.util.CityUtil;

@DirtiesContext
@SpringBootTest
public class CityServiceTests {

	private CityUtil cityUtil;

	@Autowired
	private CityService cityService;

	@BeforeEach
	public void setUp() {
		cityUtil = new CityUtil();
		cityUtil.addConnection("Boston", "New York");
		cityUtil.addConnection("Philadelphia", "Newark");
		cityUtil.addConnection("Newark", "Boston");
		cityUtil.addConnection("Trenton", "Albany");
		cityService = new CityService(cityUtil);
	}

	@Test
	public void testAreCitiesConnectedNull() {
		boolean result = cityService.areCitiesConnected(null, null);
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnectedEmpty() {
		boolean result = cityService.areCitiesConnected("", "");
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnectedWhenOriginIsEmpty() {
		boolean result = cityService.areCitiesConnected("", "Newark");
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnectedWhenDestinationIsEmpty() {
		boolean result = cityService.areCitiesConnected("Boston", "");
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnectedWhenOriginIsNull() {
		boolean result = cityService.areCitiesConnected(null, "Newark");
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnectedWhenDestinationIsNull() {
		boolean result = cityService.areCitiesConnected("Boston", null);
		assertThat(result).isFalse();
	}

	@Test
	public void testAreCitiesConnected() {
		boolean result = cityService.areCitiesConnected("Boston", "Newark");
		assertThat(result).isTrue();
	}

	@Test
	public void testAreCitiesConnectedInvalidEntires() {
		boolean result = cityService.areCitiesConnected("123", "456");
		assertThat(result).isFalse();
	}

}
