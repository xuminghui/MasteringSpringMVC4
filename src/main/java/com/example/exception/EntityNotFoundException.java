package com.example.exception;
//handle 404 status
public class EntityNotFoundException extends Exception {
	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}