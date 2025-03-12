package com.grocery.billing.service;

import java.math.BigDecimal;

public interface ExchangeRateService {
	
	public BigDecimal getConvertedAmount(String sourceCurrency, String targetCurrency, String amount);

}
