package com.grocery.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequestDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "items should not be empty")
	private Set<Item> items;
	
	@NotNull(message="bill amount is mandatory")
	private BigDecimal totalAmount;
	
	@NotBlank(message="user type is mandatory")
	private String userType;
	
	@NotBlank(message="customer tenure is mandatory")
	private int customerTenure;
	
	@NotBlank(message="source currency is mandatory")
	private String sourceCurrency;
	
	@NotBlank(message="target currency is mandatory")
	private String targetCurrency;
	

}
