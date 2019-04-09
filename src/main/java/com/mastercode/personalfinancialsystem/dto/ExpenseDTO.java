package com.mastercode.personalfinancialsystem.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseDTO {

	private BigDecimal value;
	private String description;
	private Long userDebtorId;

}
