package com.eric.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.eric.ecommerce.facades.ProductFacade;
import com.eric.ecommerce.models.Product;
import com.eric.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;
    
    private ProductFacade productFacade;
    
    public ProductService(ProductFacade productFacade) {
    	this.productFacade=productFacade;
    }
    
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
    
  //save 
    public Product updateProduct(Product product) {
    	return this.productRepository.save(product);
    }
    
    
    public boolean publishProductDetails(long productId) {
    	MessageChannel messageChannel = productFacade.outboundInventory();
	       return  messageChannel.send(MessageBuilder
	                .withPayload(getProductById(productId))
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());

    }
}
