package com.rakuten.product.controller.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 862063288043508192L;
	
	public ProductNotFoundException() {
	    super("Product not found.");
	  }

}
