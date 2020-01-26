package com.henriquealmeida.democrud.services.exception;

public class DataBaseExcepetion extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataBaseExcepetion(String message) {
		super(message);
	}

}
