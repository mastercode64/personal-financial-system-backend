package com.mastercode.personalfinancialsystem.controllers;

import com.mastercode.personalfinancialsystem.domain.Expense;
import com.mastercode.personalfinancialsystem.dto.ExpenseDTO;
import com.mastercode.personalfinancialsystem.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

	@GetMapping
	public ResponseEntity<?> findAllExpensesFromCurrentUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
		List<Expense> expenses = expenseService.findAllFromCurrentUser(page, size).getContent();
		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createExpenseForSessionUser(@RequestBody ExpenseDTO expenseDTO) {
		Expense expense = expenseService.createExpenseForSessionUser(expenseDTO);
		URI uri = linkTo(methodOn(ExpenseController.class).findById(expense.getId())).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestBody ExpenseDTO expenseDTO, @PathVariable Long id) {
		expenseService.update(expenseDTO, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		expenseService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
