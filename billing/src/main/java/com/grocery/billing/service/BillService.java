package com.grocery.billing.service;

import java.math.BigDecimal;

import com.grocery.billing.dto.BillRequestDto;


public interface BillService {

	public BigDecimal calculateBill(BillRequestDto billRequestDto);

}
