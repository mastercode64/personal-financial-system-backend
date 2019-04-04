package com.mastercode.personalfinancialsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercode.personalfinancialsystem.services.CustomUserDetailsService;


@RestController
@RequestMapping(path = "/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HomeController {

	@Autowired
	private CustomUserDetailsService sessionService;

	@GetMapping
	public ResponseEntity<?> homePage() {
		String message = "home page";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping(value = "/session")
	public ResponseEntity<?> sessionDetails() {
		return new ResponseEntity<>(sessionService.userSession(), HttpStatus.OK);
	}
}
