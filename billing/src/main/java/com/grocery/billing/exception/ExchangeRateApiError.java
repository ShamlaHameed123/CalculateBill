package com.grocery.billing.exception;

public class ExchangeRateApiError extends Exception {
	
private static final long serialVersionUID = 1L;
	
private String message;

public ExchangeRateApiError() {}

public ExchangeRateApiError(String msg) {
    super(msg);
    this.message = msg;
}

}
