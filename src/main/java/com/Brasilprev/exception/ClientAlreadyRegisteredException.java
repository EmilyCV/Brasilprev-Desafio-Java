package com.Brasilprev.exception;

public class ClientAlreadyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ClientAlreadyRegisteredException(String message) {
		super(message);
	}

}
