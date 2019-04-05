package com.mastercode.personalfinancialsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseDTO {

	private String value;
	private String description;
	private Long userDebtorId;

}
