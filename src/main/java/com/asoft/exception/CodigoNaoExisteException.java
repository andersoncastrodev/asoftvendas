package com.asoft.exception;

public class CodigoNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CodigoNaoExisteException(String mens) {
		super(mens);
	}
}
