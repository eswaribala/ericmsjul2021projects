package com.eric.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eric.ecommerce.models.Stock;

public interface StockRepository extends MongoRepository<Stock,Long>{

}
