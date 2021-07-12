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

import com.eric.ecommerce.models.Location;

import com.eric.ecommerce.services.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {
	 @Autowired 
		private LocationService locationService;
		
		@PostMapping({"/v1.0", "/v1.1"})

	    public ResponseEntity<?> saveLocation(@RequestBody Location location) {
	    	
	    	Location locationObj=this.locationService.addLocation(location);
	    	if(locationObj!=null)
	    		return ResponseEntity.status(HttpStatus.OK).body(locationObj);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("location Not Added");
	    }
		@GetMapping({"/v1.0", "/v1.1"})
		public List<Location> fetchAllLocations(){
			return this.locationService.getAllLocations();
		}
		
		@GetMapping({"/v1.0/{locationId}", "/v1.1/{locationId}"})
		public Location fetchLocationById(@PathVariable("locationId") long locationId) {
			return  this.locationService.getLocationById(locationId);
			
		}
		
		@DeleteMapping({"/v1.0/{locationId}", "/v1.1/{locationId}"})
		public ResponseEntity<?> deleteLocationById(@PathVariable("locationId") long locationId) {
			boolean status= this.locationService.deleteLocation(locationId);
			if(status)
				return ResponseEntity.status(HttpStatus.OK).body("location Deleted");
			else
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("location Not Deleted");
			
		}
		
		@PutMapping({"/v1.0", "/v1.1"})

	    public ResponseEntity<?> updateLocation(@RequestBody Location location) {
	    	
	    	Location locationObj=this.locationService.updateLocation(location);
	    	if(locationObj!=null)
	    		return ResponseEntity.status(HttpStatus.OK).body(locationObj);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("location Not Added");
	    }
}
