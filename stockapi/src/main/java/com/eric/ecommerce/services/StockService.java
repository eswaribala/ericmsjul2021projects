package com.eric.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.eric.ecommerce.models.Product;
import com.eric.ecommerce.models.Stock;
import com.eric.ecommerce.repositories.StockRepository;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
  //  @Autowired 
	//private StockRepository stockRepository;

    
    @KafkaListener(topics = "${product.topic.name}", 
			groupId = "${product.topic.group.id}")
    public void consume(String message) {
		log.info(String.format("Message recieved -> %s", message));
		
		Gson gson=new Gson();
		
		Product product= gson.fromJson(message, Product.class);
		  
		log.info(product.getName());
	}

    
}
