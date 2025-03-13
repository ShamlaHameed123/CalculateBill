package com.grocery.billing.exception;

public class CalculateBillFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public CalculateBillFailedException() {}

	public CalculateBillFailedException(String msg) {
	    super(msg);
	    this.message = msg;

}
}


