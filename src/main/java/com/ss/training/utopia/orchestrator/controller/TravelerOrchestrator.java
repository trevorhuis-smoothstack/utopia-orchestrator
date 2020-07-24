package com.ss.training.utopia.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.ss.training.utopia.orchestrator.entity.Airport;
import com.ss.training.utopia.orchestrator.entity.Booking;
import com.ss.training.utopia.orchestrator.entity.Flight;
import com.ss.training.utopia.orchestrator.entity.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200/*")
@RequestMapping("/traveler")
public class TravelerOrchestrator {

    private final String travelerBase = "http://localhost:8083/traveler";
    
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
	RestTemplate restTemplate;
    
    @GetMapping(path="/travelers/{travelerId}/flights/departure/{departId}/arrival/{arriveId}")
	public ResponseEntity<Flight[]> readFlights(RequestEntity<?> request,@PathVariable Long travelerId, @PathVariable Long departId, @PathVariable Long arriveId) {
		try {
			return restTemplate.exchange(travelerBase + "/travelers/"+travelerId+"/flights/departure/"+departId+"/arrival/"+arriveId, HttpMethod.GET, request, Flight[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight[]>((Flight[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path="/travelers/{travelerId}/flights")
	public ResponseEntity<Flight[]> readAllFlight(RequestEntity<?> request, @PathVariable Long travelerId) {
		try {
			return restTemplate.exchange(travelerBase + "/travelers/"+travelerId+"/flights", HttpMethod.GET, request, Flight[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight[]>((Flight[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
	
	@GetMapping(path="/flights/{flightId}")
	public ResponseEntity<Flight> readFlightById(RequestEntity<?> request, @PathVariable Long flightId) {
		try {
			return restTemplate.exchange(travelerBase + "/flights/"+flightId, HttpMethod.GET, request, Flight.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Flight>((Flight) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/airports")
	public ResponseEntity<Airport[]> readAirports(RequestEntity<?> request) {
		try {
			return restTemplate.exchange(travelerBase + "/airports", HttpMethod.GET, request, Airport[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Airport[]>((Airport[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/bookings/{travelerId}")
	public ResponseEntity<Booking[]> readBookings(@PathVariable Long travelerId, RequestEntity<?> request) {
		try {
			return restTemplate.exchange(travelerBase + "/bookings/" + travelerId, HttpMethod.GET, request, Booking[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking[]>((Booking[]) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/travelers/{userId}")
	public ResponseEntity<User> readUserById(@PathVariable Long userId, RequestEntity<?> request) {
		try {
			return restTemplate.exchange(travelerBase + "/users/" + userId, HttpMethod.GET, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>((User) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@GetMapping(path = "/users/{username}")
	public ResponseEntity<User> readUserByUsername(@PathVariable String username, RequestEntity<?> request) {
		try {
			return restTemplate.exchange(travelerBase + "/users/" + username, HttpMethod.GET, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>((User) null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(RequestEntity<User> request) {
		try {
			return restTemplate.exchange(travelerBase + "/users", HttpMethod.POST, request, User.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<User>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PostMapping(path = "/bookings")
	public ResponseEntity<Booking> createBooking(RequestEntity<Booking> request) {
		try {
			return restTemplate.exchange(travelerBase + "/bookings", HttpMethod.POST, request, Booking.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}

	@PutMapping(path = "/bookings")
	public ResponseEntity<Booking> cancelBooking(RequestEntity<Booking> request) {
		try {
			return restTemplate.exchange(travelerBase + "/bookings", HttpMethod.PUT, request, Booking.class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
	
	@GetMapping(path = "/travelers/{travelerId}/bookings")
	public ResponseEntity<Booking[]> readBookedFlights(RequestEntity<Booking[]> request, @PathVariable Long travelerId) {
		try {
			return restTemplate.exchange(travelerBase + "/travelers/"+travelerId+"/bookings", HttpMethod.PUT, request, Booking[].class);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Booking[]>(request.getBody(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
	
	@GetMapping( path = "/authorized")
	public ResponseEntity<Object> checkAuthorization(){
		try {
			return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
		} catch (RestClientResponseException e) {
			return new ResponseEntity<Object>(null, HttpStatus.valueOf(e.getRawStatusCode()));
		}
	}
	
	@GetMapping(path="/user/hi")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("hi", HttpStatus.OK);
}
