package com.eric.ecommerce.facades;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProductFacade.class)
public class StreamConfig {

}
