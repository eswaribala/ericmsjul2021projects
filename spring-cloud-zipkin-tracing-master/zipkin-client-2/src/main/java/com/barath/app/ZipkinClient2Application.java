package com.barath.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
public class ZipkinClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinClient2Application.class, args);
	}
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	
	@Configuration
	@EnableSwagger2
	public class SwaggerConfiguration{
	
		@Bean
		public Docket docker(){
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
		}
			

	}
}
