package com.mastercode.personalfinancialsystem.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mastercode.personalfinancialsystem.domain.Expense;
import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.dto.ExpenseDTO;
import com.mastercode.personalfinancialsystem.exception.ResourceNotFoundException;
import com.mastercode.personalfinancialsystem.exception.ValidationErrorException;
import com.mastercode.personalfinancialsystem.respository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private UserService userService;

	public Expense findById(Long id) {
		return expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Expense " + id + " not found"));
	}
	
	public Page<Expense> findAll(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(10));
		Page<Expense> userPage = expenseRepository.findAll(pageable);
		return userPage;
	}
	

	public Expense createExpenseForSessionUser(ExpenseDTO expenseDTO) {
		User userCreator = userService.getUserFromSession();
		User userDebtor = null;
		
		if(expenseDTO.getUserDebtorId() != null) {
			userDebtor = userService.findById(expenseDTO.getUserDebtorId());
		}
		
		if(userCreator.getId() == Optional.ofNullable(userDebtor).map(User::getId).orElse(null))
			throw new ValidationErrorException("Expense debtor cannot be the creator");
		
		Expense expense = new Expense(
				expenseDTO.getDescription(),
				expenseDTO.getValue(),
				userCreator,
				userDebtor);

		return createExpense(expense);
	}
	
	public Expense createExpense(@Valid Expense expense) {	
		return expenseRepository.save(expense);
	}

	public void delete(Long id) {
		expenseRepository.delete(this.findById(id));		
	}
	
}
