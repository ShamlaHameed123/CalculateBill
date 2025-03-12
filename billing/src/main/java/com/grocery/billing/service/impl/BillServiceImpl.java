package com.grocery.billing.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.billing.dto.BillRequestDto;
import com.grocery.billing.dto.Item;
import com.grocery.billing.service.BillService;
import com.grocery.billing.service.ExchangeRateService;

@Service
public class BillServiceImpl implements BillService{

	
	@Autowired
	private ExchangeRateService exchangeRateService;


	@Override
	public BigDecimal calculateBill(BillRequestDto billRequestDto) {
		BigDecimal totalBill = BigDecimal.ZERO;
		BigDecimal nonGroceryTotal = BigDecimal.ZERO;
		for(Item item : billRequestDto.getItems()) {
			totalBill = totalBill.add(item.getPrice());
			if(!item.getCategory().equalsIgnoreCase("GROCERY")) {
				nonGroceryTotal = nonGroceryTotal.add(item.getPrice());
			}
		}
		
		double discountRate = 0;
		String userType = billRequestDto.getUserType();
		if(userType.equalsIgnoreCase("EMPLOYEE")) {
			discountRate = 0.30;
		}
		else if(userType.equalsIgnoreCase("AFFILIATE")) {
			discountRate = 0.10;
		}
		else if(userType.equalsIgnoreCase("CUSTOMER") && billRequestDto.getCustomerTenure() > 2) {
			discountRate = 0.05;
		}
		
		BigDecimal discountedAmount = nonGroceryTotal.multiply(BigDecimal.valueOf(discountRate));
		BigDecimal flatDiscountAmount = totalBill.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(5));
		BigDecimal netAmount = totalBill.subtract(discountedAmount).subtract(flatDiscountAmount);
		BigDecimal convertedAmount = exchangeRateService.getConvertedAmount(billRequestDto.getSourceCurrency(), billRequestDto.getTargetCurrency(), netAmount.toString());
		return convertedAmount;

	}

}
