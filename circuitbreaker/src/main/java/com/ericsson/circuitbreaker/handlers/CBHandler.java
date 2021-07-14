package com.ericsson.circuitbreaker.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.ericsson.circuitbreaker.models.JWTRequest;

@Service
public class CBHandler {

    @Autowired
	private RestTemplate restTemplate;
    @Value("${serviceUrl}")
    private String serviceUrl;
    @Value("${fallBackUrl}")
    private String fallBackUrl;
   
    @HystrixCommand(commandKey = "GetProductCommand", groupKey = "ProductGroupKey", threadPoolKey = "Test",
    		fallbackMethod = "fallbackRequestHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "110"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            })
       /**
    @HystrixCommand(commandKey = "GetCustomerCommand", groupKey = "CustomerGroupKey", threadPoolKey = "Test",
    		fallbackMethod = "fallbackRequestHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "false"),
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
            }
		
            )
    **/
   // @HystrixCommand(fallbackMethod = "fallbackRequestHandler")
	public ResponseEntity<String> requestHandler(JWTRequest  jwtRequest)
	{
    	
    	//return restTemplate.exchange(serviceUrl+"?userName="+jwtRequest.getUserName()
		//+"&userPwd="+jwtRequest.getUserPwd(),HttpMethod.GET,null,String.class);
		
		return restTemplate.exchange(serviceUrl+"?userName="+jwtRequest.getUserName()
		+"&userPwd="+jwtRequest.getPassword(),HttpMethod.GET,null,String.class);
		
		
	}
	
	public ResponseEntity<String> fallbackRequestHandler(JWTRequest  jwtRequest)
	{
		
		return restTemplate.exchange(fallBackUrl,HttpMethod.GET,null,String.class);
		
		
	}

}
