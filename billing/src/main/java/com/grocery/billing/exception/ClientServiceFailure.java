package com.grocery.billing.exception;

public class ClientServiceFailure extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	private String message;

    public ClientServiceFailure() {}

    public ClientServiceFailure(String msg) {
        super(msg);
        this.message = msg;
    }
}
