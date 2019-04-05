package com.mastercode.personalfinancialsystem.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercode.personalfinancialsystem.domain.Expense;
import com.mastercode.personalfinancialsystem.dto.ExpenseDTO;
import com.mastercode.personalfinancialsystem.services.ExpenseService;

@RestController
@RequestMapping(path = "/expenses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Expense expense = expenseService.findById(id);
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createExpenseForSessionUser(@RequestBody ExpenseDTO expenseDTO) {
		Expense expense = expenseService.createExpenseForSessionUser(expenseDTO);
		URI uri = linkTo(methodOn(ExpenseController.class).findById(expense.getId())).toUri();
		return ResponseEntity.created(uri).build();
	}
}
