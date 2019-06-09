package com.rakuten.product.controller.exception;

public class ThirdPartyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230442007373561495L;

	public ThirdPartyException() {
		super("The convertion could not be done");
	}
}
