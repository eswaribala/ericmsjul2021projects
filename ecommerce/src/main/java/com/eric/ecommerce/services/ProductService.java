package com.eric.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.ecommerce.models.Product;
import com.eric.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;
    
    //save 
    public Product addProduct(Product product) {
    	return this.productRepository.save(product);
    }
    
    public List<Product> getAllProducts(){
    	return this.productRepository.findAll();
    }

    
    public Product getProductById(long productId) {
    	return this.productRepository.findById(productId).orElse(null);
    }
    
    
    public boolean deleteProduct(long productId) {
    	boolean status=true;
    	this.productRepository.deleteById(productId);
    	if(this.getProductById(productId)!=null)
    		status=false;
    	
    	return status;
    }
    
}
