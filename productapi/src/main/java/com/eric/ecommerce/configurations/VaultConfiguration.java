package com.eric.ecommerce.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties
public class VaultConfiguration {

	private String username;
	private String password;
}
