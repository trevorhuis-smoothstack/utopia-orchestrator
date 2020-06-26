package com.ss.training.utopia.orchestrator.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Justin O'Brien
 */
@RestController
@CrossOrigin
@RequestMapping("/counter")
public class CounterOrchestrator {

	@Autowired
	RestTemplate template;

	private final String baseUrl = "http://localhost:8081/counter";

	@RequestMapping(method = RequestMethod.HEAD, path = "/traveler/{username}")
	public ResponseEntity<Object> userIsTraveler(@PathVariable String username, RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/traveler/{username}", HttpMethod.HEAD, request, Object.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

}
