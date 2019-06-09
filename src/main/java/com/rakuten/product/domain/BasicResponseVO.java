package com.rakuten.product.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BasicResponseVO {
	
	private HttpStatus status;
	
	private List<String> messages = new ArrayList<>();
	
}
