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
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eric.ecommerce.models.Product;
import com.eric.ecommerce.services.ProductService;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired 
	private ProductService productService;
	
	@PostMapping({"/v1.0", "/v1.1"})

    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
    	
    	Product productObj=this.productService.addProduct(product);
    	if(productObj!=null)
    		return ResponseEntity.status(HttpStatus.OK).body(productObj);
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Added");
    }
	@GetMapping({"/v1.0", "/v1.1"})
	public List<Product> fetchAllProducts(){
		return this.productService.getAllProducts();
	}
	
	@GetMapping({"/v1.0/{productId}", "/v1.1/{productId}"})
	public Product fetchProductById(@PathVariable("productId") long productId) {
		return  this.productService.getProductById(productId);
		
	}
	
	@DeleteMapping({"/v1.0/{productId}", "/v1.1/{productId}"})
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") long productId) {
		boolean status= this.productService.deleteProduct(productId);
		if(status)
			return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
		else
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Deleted");
		
	}
	
	@PutMapping({"/v1.0", "/v1.1"})

    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
    	
    	Product productObj=this.productService.updateProduct(product);
    	if(productObj!=null)
    		return ResponseEntity.status(HttpStatus.OK).body(productObj);
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not Added");
    }
}
