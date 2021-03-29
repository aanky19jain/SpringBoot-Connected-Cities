package com.cities.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.cities.util.Constants;

@DirtiesContext
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CityControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testAreCitiesConnected() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Newark");
		ResponseEntity<String> response = restTemplate
				.getForEntity("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.getBody()).isEqualTo(Constants.YES);
	}

	@Test
	public void testAreCitiesConnectedNo() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Canton");
		queryParams.put("destination", "Newark");
		ResponseEntity<String> response = restTemplate
				.getForEntity("/connected?origin={origin}&destination={destination}", String.class, queryParams);
		assertThat(response.getBody()).isEqualTo(Constants.NO);
	}

	@Test
	public void testAreCitiesConnectedInvalidRequest() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Canton");
		queryParams.put("destination", "Newark");
		ResponseEntity<String> response = restTemplate.getForEntity("/?origin={origin}&destination={destination}",
				String.class, queryParams);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
