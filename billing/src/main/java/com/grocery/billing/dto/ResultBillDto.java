package com.grocery.billing.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBillDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String amount;

}
