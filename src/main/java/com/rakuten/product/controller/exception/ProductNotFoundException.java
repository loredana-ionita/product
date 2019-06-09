package com.rakuten.product.controller.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 862063288043508192L;
	
	private final String id;

	public ProductNotFoundException(final String id) {
	    super("Product not found with id: " + id);
	    this.id = id;
	  }

}
