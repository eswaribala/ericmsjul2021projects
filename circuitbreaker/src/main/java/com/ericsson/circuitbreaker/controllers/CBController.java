package com.ericsson.circuitbreaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.circuitbreaker.handlers.CBHandler;
import com.ericsson.circuitbreaker.models.JWTRequest;

@RestController
public class CBController {
    @Autowired
	private CBHandler cbHandler;
    
	@PostMapping("/")
	public ResponseEntity<?> sendRequest(@RequestBody JWTRequest jwtRequest) {
		 return this.cbHandler.requestHandler(jwtRequest);
	}
}
