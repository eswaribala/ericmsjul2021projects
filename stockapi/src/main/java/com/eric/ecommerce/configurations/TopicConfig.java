package com.eric.ecommerce.configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfig 
{
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${product.topic.name}")
	private String productTopicName;

	@Value(value = "${stock.topic.name}")
	private String stockTopicName;

	@Bean
	public NewTopic productTopic() {
		return TopicBuilder.name(productTopicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}

	@Bean
	public NewTopic stockTopic() {
		return TopicBuilder.name(stockTopicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}
	
	//If not using spring boot
	
	@Bean
    public KafkaAdmin kafkaAdmin() 
	{
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
}
