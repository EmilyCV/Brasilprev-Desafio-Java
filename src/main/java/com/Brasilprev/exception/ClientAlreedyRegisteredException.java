package com.Brasilprev.exception;

public class ClientAlreedyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ClientAlreedyRegisteredException(String message) {
		super(message);
	}

}
