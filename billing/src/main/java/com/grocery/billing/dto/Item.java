package com.grocery.billing.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "item Name should not be empty")
	private String itemName;
	
	@NotEmpty(message = "item category should not be empty")
	private String category;
	
	@NotNull(message = "price should not be empty")
	private BigDecimal price;


}
