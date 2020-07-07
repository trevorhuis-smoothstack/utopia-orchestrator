package com.ss.training.utopia.orchestrator.controller;

import com.ss.training.utopia.orchestrator.entity.Airport;
import com.ss.training.utopia.orchestrator.entity.Booking;
import com.ss.training.utopia.orchestrator.entity.Flight;
import com.ss.training.utopia.orchestrator.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Trevor Huis in 't Veld
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
@RequestMapping("/agent")
public class AgentOrchestrator {

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

	@GetMapping(path = "/airports")
	public ResponseEntity<Airport[]> readAirports(RequestEntity<?> request) {
		try {
			return restTemplate.exchange(agentBase + "/airports", HttpMethod.GET, request, Airport[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Airport[]>((Airport[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/bookings/{agentId}")
	public ResponseEntity<Booking[]> readBookings(@PathVariable Long agentId, RequestEntity<?> request) {
		try {
			return restTemplate.exchange(agentBase + "/bookings/" + agentId, HttpMethod.GET, request, Booking[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking[]>((Booking[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/user/{username}")
	public ResponseEntity<User> readUser(@PathVariable String username, RequestEntity<?> request) {
		try {
			return restTemplate.exchange(agentBase + "/user/" + username, HttpMethod.GET, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>((User) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping(path = "/user")
	public ResponseEntity<User> createUser(RequestEntity<User> request) {
		try {
			return restTemplate.exchange(agentBase + "/user", HttpMethod.POST, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping(path = "/booking")
	public ResponseEntity<Booking> createBooking(RequestEntity<Booking> request) {
		try {
			return restTemplate.exchange(agentBase + "/booking", HttpMethod.POST, request, Booking.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PutMapping(path = "/booking")
	public ResponseEntity<Booking> cancelBooking(RequestEntity<Booking> request) {
		try {
			return restTemplate.exchange(agentBase + "/booking", HttpMethod.PUT, request, Booking.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
}