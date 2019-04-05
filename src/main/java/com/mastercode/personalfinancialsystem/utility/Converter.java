package com.mastercode.personalfinancialsystem.utility;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.mastercode.personalfinancialsystem.exception.InvalidFormatException;

public class Converter {

	public static BigDecimal stringToBigDecimal(String value) {
		int numberComma = StringUtils.countOccurrencesOf(value, ",");
		int numberPeriod = StringUtils.countOccurrencesOf(value, ".");
		if(numberComma > 0)
			throw new InvalidFormatException("Invalid number format: '" + value + "' use Period instead");
		
		if(numberPeriod > 1)
			throw new InvalidFormatException("Invalid number format: '" + value + "' use only one Period");

		return new BigDecimal(value);
	}
}
