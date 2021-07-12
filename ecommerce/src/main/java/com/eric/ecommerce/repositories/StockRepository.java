package com.eric.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eric.ecommerce.models.Stock;

public interface StockRepository extends JpaRepository<Stock,Long>{

}
