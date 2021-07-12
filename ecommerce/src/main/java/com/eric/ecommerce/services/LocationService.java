package com.eric.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.ecommerce.models.Location;
import com.eric.ecommerce.repositories.LocationRepository;

@Service
public class LocationService {
	  @Autowired
		private LocationRepository locationRepository;
	    
	    //save 
	    public Location addLocation(Location location) {
	    	return this.locationRepository.save(location);
	    }
	    
	    public List<Location> getAllLocations(){
	    	return this.locationRepository.findAll();
	    }

	    
	    public Location getLocationById(long locationId) {
	    	return this.locationRepository.findById(locationId).orElse(null);
	    }
	    
	    
	    public boolean deleteLocation(long locationId) {
	    	boolean status=true;
	    	this.locationRepository.deleteById(locationId);
	    	if(this.getLocationById(locationId)!=null)
	    		status=false;
	    	
	    	return status;
	    }
	    //save 
	    public Location updateLocation(Location location) {
	    	return this.locationRepository.save(location);
	    }
}
