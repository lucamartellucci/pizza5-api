package com.absontheweb.pizza5.service.exception;

public class MenuServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public MenuServiceException() {
		super();
	}

	public MenuServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MenuServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MenuServiceException(String message) {
		super(message);
	}

	public MenuServiceException(Throwable cause) {
		super(cause);
	}
	
	

}
