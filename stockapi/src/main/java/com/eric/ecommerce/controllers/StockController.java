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
import com.eric.ecommerce.services.DeliverySchedulePublisher;
import com.eric.ecommerce.services.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	  @Autowired 
      private DeliverySchedulePublisher deliverySchedulePublisher;
	
	  @GetMapping("/{productId}")
	  public void publishDeliverySchedule(@PathVariable("productId") long productId) {
		  
		  this.deliverySchedulePublisher.sendMessage(productId);
			  
		  
	  }
	  
}
