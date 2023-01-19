package com.asoft.exception;

public class ErroChaveEstrangueiraEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroChaveEstrangueiraEmUsoException(String mens) {
		super(mens);
	}
}
