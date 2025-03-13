package com.grocery.billing.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.billing.dto.BillRequestDto;
import com.grocery.billing.dto.Item;
import com.grocery.billing.exception.CalculateBillFailedException;
import com.grocery.billing.exception.ExchangeRateApiError;
import com.grocery.billing.service.BillService;
import com.grocery.billing.service.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BillServiceImpl implements BillService{
	
	private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

	
	@Autowired
	private ExchangeRateService exchangeRateService;


	@Override
	public BigDecimal calculateBill(BillRequestDto billRequestDto) throws ExchangeRateApiError, CalculateBillFailedException {
		try {
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
		return exchangeRateService.getConvertedAmount(billRequestDto.getSourceCurrency(), billRequestDto.getTargetCurrency(), netAmount.toString());
		}
		catch(ExchangeRateApiError e) {
			logger.error("Exception caught " , e.getMessage(), e);
            throw new ExchangeRateApiError(e.getMessage());
		}
		catch(Exception e) {
			logger.error("error calculating bill ", e.getLocalizedMessage(), e);
			throw new CalculateBillFailedException("Error calculating bill");
		}

	}

}
