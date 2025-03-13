package com.grocery.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@Min(value = 1, message = "invalid total bill amount")
	private BigDecimal totalAmount;
	
	@NotBlank(message="user type is mandatory")
	private String userType;
	
	@NotNull(message="customer tenure is mandatory")
	@Min(value = 0, message = "invalid customer tenure")
	private Integer customerTenure;
	
	@NotBlank(message="source currency is mandatory")
	private String sourceCurrency;
	
	@NotBlank(message="target currency is mandatory")
	private String targetCurrency;
	

}
