package com.barath.app;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="client",url="${microservice.client.url}")
public interface Client1 {
	
	@GetMapping(value="/")
	public String home();

}
