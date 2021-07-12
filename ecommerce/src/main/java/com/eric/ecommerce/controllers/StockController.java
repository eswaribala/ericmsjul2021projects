package com.eric.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.ecommerce.models.Stock;
import com.eric.ecommerce.services.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	  @Autowired 
		private StockService stockService;
		
		@PostMapping({"/v1.0/{productId}/{locationId}", "/v1.1/{productId}/{locationId}"})

	    public ResponseEntity<?> saveStock(@RequestBody Stock stock,
	    		@PathVariable("productId") long productId,@PathVariable("locationId") long locationId) {
	    	
	    	Stock stockObj=this.stockService.addStock(stock,productId,locationId);
	    	if(stockObj!=null)
	    		return ResponseEntity.status(HttpStatus.OK).body(stockObj);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("stock Not Added");
	    }
		@GetMapping({"/v1.0", "/v1.1"})
		public List<Stock> fetchAllStocks(){
			return this.stockService.getAllStocks();
		}
		
		@GetMapping({"/v1.0/{stockId}", "/v1.1/{stockId}"})
		public Stock fetchStockById(@PathVariable("stockId") long stockId) {
			return  this.stockService.getStockById(stockId);
			
		}
		
		@DeleteMapping({"/v1.0/{stockId}", "/v1.1/{stockId}"})
		public ResponseEntity<?> deleteStockById(@PathVariable("stockId") long stockId) {
			boolean status= this.stockService.deleteStock(stockId);
			if(status)
				return ResponseEntity.status(HttpStatus.OK).body("stock Deleted");
			else
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("stock Not Deleted");
			
		}
		
		@PutMapping({"/v1.0/{productId}/{locationId}", "/v1.1/{productId}/{locationId}"})

	    public ResponseEntity<?> updateStock(@RequestBody Stock stock,@PathVariable("productId") long productId,@PathVariable("locationId") long locationId) {
	    	
	    	Stock stockObj=this.stockService.updateStock(stock,productId,locationId);
	    	if(stockObj!=null)
	    		return ResponseEntity.status(HttpStatus.OK).body(stockObj);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("stock Not Added");
	    }
}
