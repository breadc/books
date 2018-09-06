package com.exception;

import java.util.concurrent.locks.Lock;

/**
 * 
 * @author chenghui 单例异常
 */
public class GenertorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	private GenertorException(String message) {
		super();
		this.message = message;
	}


	private static GenertorException genertorException = null;

	public static synchronized GenertorException getInstance(String message) {
		if (genertorException == null) {
			synchronized (GenertorException.class) {
				if (genertorException == null) {
					genertorException = new GenertorException(message);
				}
			}
		}
		return genertorException;
	}


}
