package com.eric.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.ecommerce.models.Location;
import com.eric.ecommerce.models.Product;
import com.eric.ecommerce.models.Stock;
import com.eric.ecommerce.repositories.StockRepository;

@Service
public class StockService {
    @Autowired 
	private StockRepository stockRepository;
    @Autowired 
    private ProductService productService;
    @Autowired 
    private LocationService locationService;
    
    public Stock addStock(Stock stock,long productId,long locationId) {
    	
    	Product productObj=this.productService.getProductById(productId);
    	Location locationObj=this.locationService.getLocationById(locationId);
    	if((productObj!=null)&&(locationObj!=null)) {
    		stock.setProduct(productObj);
            stock.setLocation(locationObj);    		
    	}
    	
    	return this.stockRepository.save(stock);
    }
    public List<Stock> getAllStocks(){
    	return this.stockRepository.findAll();
    }

    
    public Stock getStockById(long stockId) {
    	return this.stockRepository.findById(stockId).orElse(null);
    }
    
    
    public boolean deleteStock(long stockId) {
    	boolean status=true;
    	this.stockRepository.deleteById(stockId);
    	if(this.getStockById(stockId)!=null)
    		status=false;
    	
    	return status;
    }
  public Stock updateStock(Stock stock,long productId,long locationId) {
    	
    	Product productObj=this.productService.getProductById(productId);
    	Location locationObj=this.locationService.getLocationById(locationId);
    	if((productObj!=null)&&(locationObj!=null)) {
    		stock.setProduct(productObj);
            stock.setLocation(locationObj);    		
    	}
    	
    	return this.stockRepository.save(stock);
    }
}
