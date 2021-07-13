package com.eric.ecommerce.configurations;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Data
@Slf4j
@EnableConfigurationProperties(VaultConfiguration.class)

public class DBConfiguration {

    @Value("${db_url}")
	private String url;    
   // @Value("${db_username}")
	//private String userName;
   // @Value("${db_password}")
	//private String password;
    @Value("${db_driver}")
	private String driver;
    private DataSourceBuilder dataSourceBuilder;
    private final VaultConfiguration vaultConfiguration;
    
    public DBConfiguration(VaultConfiguration vaultConfig)
    {
    	this.vaultConfiguration=vaultConfig;
    }
    
    @Bean
    //@Profile("prod")
    public DataSource getDataSource()
    {
    	log.info("Entering Production Env.....");
        log.info("User Name..."+vaultConfiguration.getUsername());
    	log.info("User Name..."+vaultConfiguration.getPassword());
    	dataSourceBuilder=DataSourceBuilder.create();
    	dataSourceBuilder.url(url);
    	//dataSourceBuilder.username(userName);
    	//dataSourceBuilder.password(password);
    	dataSourceBuilder.username(vaultConfiguration.getUsername());
    	dataSourceBuilder.password(vaultConfiguration.getPassword());
    	dataSourceBuilder.driverClassName(driver);
    	return dataSourceBuilder.build();
   	
    }

}
