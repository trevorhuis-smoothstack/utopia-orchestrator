package com.ss.training.utopia.orchestrator.controller;

import com.ss.training.utopia.orchestrator.entity.Booking;
import com.ss.training.utopia.orchestrator.entity.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Trevor Huis in 't Veld
 */
@RestController
@CrossOrigin
@RequestMapping("/agent")
public class AgentController {

    private final String agentBase = "http://localhost:8082/agent";

    @Autowired
	RestTemplate restTemplate;
    
    @GetMapping(path = "/flights")
	public ResponseEntity<Flight[]> readFlights(RequestEntity<?> request) {
		try {
			return restTemplate.exchange(agentBase + "/flights", HttpMethod.GET, request, Flight[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight[]>((Flight[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
}