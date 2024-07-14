 package com.demo.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static String msg = "Provided Id is not available at server";
	
	public CustomerNotFoundException(String msgs) {
		super(msgs);
		msg = msgs;
	}
	public CustomerNotFoundException() {
		super(msg);
	}
	@Override
	public String toString() {
		return "CustomerNotFoundException [msg=" + msg + "]";
	}
	
}
