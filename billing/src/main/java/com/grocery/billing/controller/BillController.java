package com.grocery.billing.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.billing.dto.BillRequestDto;
import com.grocery.billing.dto.ResultBillDto;
import com.grocery.billing.service.BillService;


@RestController
@RequestMapping(value="/bills")
public class BillController {
	
	
	@Autowired
	private BillService billService;
	
	@GetMapping("/api/calculate")
	public ResponseEntity<ResultBillDto> calculateNetAmount(@RequestBody @Valid BillRequestDto billRequestDto) {
		BigDecimal netAmount = billService.calculateBill(billRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResultBillDto(netAmount.setScale(2, RoundingMode.HALF_EVEN) + " " + billRequestDto.getTargetCurrency()));
		
	}

}
