package com.grocery.billing.service;

import java.math.BigDecimal;

import com.grocery.billing.exception.ExchangeRateApiError;

public interface ExchangeRateService {
	
	public BigDecimal getConvertedAmount(String sourceCurrency, String targetCurrency, String amount) 
																			throws ExchangeRateApiError;

}
