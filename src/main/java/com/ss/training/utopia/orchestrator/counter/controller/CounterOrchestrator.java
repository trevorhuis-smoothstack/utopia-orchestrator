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

import com.ss.training.utopia.orchestrator.entity.Airport;
import com.ss.training.utopia.orchestrator.entity.Booking;
import com.ss.training.utopia.orchestrator.entity.Flight;
import com.ss.training.utopia.orchestrator.security.User;

/**
 * @author Justin O'Brien
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
@RequestMapping("/counter")
public class CounterOrchestrator {

	@Autowired
	RestTemplate template;

	private final String baseUrl = "http://localhost:8081/counter";

	@RequestMapping(/*method = RequestMethod.HEAD,*/ path = "/traveler/{username}")
	public ResponseEntity<Object> userIsTraveler(@PathVariable String username, RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/traveler/" + username, HttpMethod.HEAD, request, Object.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username, RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/users/" + username, HttpMethod.GET, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>((User) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping("/airports")
	public ResponseEntity<Airport[]> getAllAirports(RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/airports", HttpMethod.GET, request, Airport[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Airport[]>((Airport[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping("/flights/cancellable/traveler/{travelerId}")
	public ResponseEntity<Flight[]> getCancellablyBookedFlights(@PathVariable Long travelerId,
			RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/flights/cancellable/traveler/" + travelerId, HttpMethod.GET, request,
					Flight[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight[]>((Flight[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PutMapping("/bookings/traveler/{travelerId}/flight/{flightId}")
	public ResponseEntity<Object> cancelBooking(@PathVariable Long travelerId, @PathVariable Long flightId,
			RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/bookings/traveler/" + travelerId + "/flight/" + flightId,
					HttpMethod.PUT, request, Object.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/user", HttpMethod.POST, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>((User) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@RequestMapping(/* method = RequestMethod.HEAD, */ path = "/user/{username}")
	public ResponseEntity<Object> usernameAvailable(@PathVariable String username, RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/user/" + username, HttpMethod.HEAD, request, Object.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping("/flights/bookable/departure/{departId}/arrival/{arriveId}/traveler/{travelerId}")
	public ResponseEntity<Flight[]> getBookableFlights(@PathVariable Long departId, @PathVariable Long arriveId,
			@PathVariable Long travelerId, RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/flights/bookable/departure/" + departId + "/arrival/" + arriveId
					+ "/traveler/" + travelerId, HttpMethod.GET, request, Flight[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight[]>((Flight[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping("/booking")
	public ResponseEntity<Object> bookFlight(RequestEntity<?> request) {
		try {
			return template.exchange(baseUrl + "/booking", HttpMethod.POST, request, Object.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
	
	@RequestMapping(/* method = RequestMethod.HEAD, */ path = "/authorized")
	public ResponseEntity<Object> checkAuthorization(){
		try {
			return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

}
