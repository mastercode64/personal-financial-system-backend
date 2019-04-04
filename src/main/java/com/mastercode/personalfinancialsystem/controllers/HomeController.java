package com.mastercode.personalfinancialsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HomeController {

	@GetMapping
	public ResponseEntity<?> homePage() {
		String message = "home page";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
