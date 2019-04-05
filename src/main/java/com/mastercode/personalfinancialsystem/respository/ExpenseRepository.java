package com.mastercode.personalfinancialsystem.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mastercode.personalfinancialsystem.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
