package com.grocery.billing.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateApiError extends Exception {
	
private static final long serialVersionUID = 1L;
	
	private String message;

}
