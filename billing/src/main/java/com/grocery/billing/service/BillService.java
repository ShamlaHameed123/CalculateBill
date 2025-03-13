package com.grocery.billing.service;

import java.math.BigDecimal;

import com.grocery.billing.dto.BillRequestDto;
import com.grocery.billing.exception.CalculateBillFailedException;
import com.grocery.billing.exception.ExchangeRateApiError;


public interface BillService {

	public BigDecimal calculateBill(BillRequestDto billRequestDto) throws CalculateBillFailedException, ExchangeRateApiError;

}
