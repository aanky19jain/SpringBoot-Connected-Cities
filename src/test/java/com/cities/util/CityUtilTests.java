package com.cities.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
@SpringBootTest
public class CityUtilTests {

	@Autowired
	private CityUtil cityUtil;

	@Test
	public void testIsValidCityNull() {
		boolean result = cityUtil.isValidCity(null);
		assertThat(result).isFalse();
	}

	@Test
	public void testIsValidCity() {
		boolean result = cityUtil.isValidCity("Boston");
		assertThat(result).isTrue();
	}

	@Test
	public void testGetMao() {
		Map<String, List<String>> result = cityUtil.getCityMap();
		assertThat(result.size()).isEqualTo(6);
	}

	@Test
	public void testGetConnectedCities() {
		List<String> result = cityUtil.getConnectedCities("Boston");
		assertThat(result.size()).isEqualTo(2);
	}

	@Test
	public void testGetConnectedCitiesInvalidCity() {
		List<String> result = cityUtil.getConnectedCities("Canton");
		assertThat(result).isNull();
	}

}
