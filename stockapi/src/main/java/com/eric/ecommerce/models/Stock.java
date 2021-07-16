package com.eric.ecommerce.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "StockStatusHistories")

public class Stock {
	@Id
	private long stockId;	
	private long qty;
	private Product product;


}
