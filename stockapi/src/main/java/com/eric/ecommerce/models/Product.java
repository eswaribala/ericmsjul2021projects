package com.eric.ecommerce.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private long productId;
	
	private String name;
	//@DateTimeFormat(iso = ISO.DATE)

	private String dop;
	
	private long cost;


	private ProductType productType;	

}
