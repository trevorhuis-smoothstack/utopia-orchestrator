package com.ss.training.utopia.orchestrator.counter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Justin O'Brien
 */
@RestController
@CrossOrigin
@RequestMapping("/counter")
public class CounterOrchestrator {

	@GetMapping("/test")
	public ResponseEntity<Object> verySimple() {
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}

}
