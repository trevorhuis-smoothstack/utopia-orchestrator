package com.ss.training.utopia.orchestrator.counter.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.ss.training.utopia.orchestrator.counter.entity.Airport;
import com.ss.training.utopia.orchestrator.counter.entity.Booking;
import com.ss.training.utopia.orchestrator.counter.entity.Flight;
import com.ss.training.utopia.orchestrator.counter.entity.User;

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

	@GetMapping("/users/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
	}

	@GetMapping("/airports")
	public ResponseEntity<Airport[]> getAllAirports() {

	}

	@GetMapping("/flights/cancellable/traveler/{travelerId}")
	public ResponseEntity<Flight[]> getCancellablyBookedFlights(@PathVariable Long travelerId) {

	}

	@PutMapping("/bookings/traveler/{travelerId}/flight/{flightId}")
	public ResponseEntity<Object> cancelBooking(@PathVariable Long travelerId, @PathVariable long flightId) {

	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {

	}

	@RequestMapping(method = RequestMethod.HEAD, path = "/user/{username}")
	public ResponseEntity<Object> usernameAvailable(@PathVariable String username) {

	}

	@GetMapping("flights/bookable/departure/{departId}/arrival/{arriveId}/traveler/{travelerId}")
	public ResponseEntity<Flight[]> getBookableFlights(@PathVariable Long departId, @PathVariable Long arriveId,
			@PathVariable Long travelerId) {

	}

	@PostMapping("/booking")
	public ResponseEntity<Object> bookFlight(@RequestBody Booking booking) {

	}

}
