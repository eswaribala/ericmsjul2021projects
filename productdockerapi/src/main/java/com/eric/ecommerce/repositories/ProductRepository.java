package com.eric.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eric.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
