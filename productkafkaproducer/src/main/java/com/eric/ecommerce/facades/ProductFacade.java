package com.eric.ecommerce.facades;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductFacade {

    String OUTPUT = "product-out";
   
    @Output(OUTPUT)
    MessageChannel outboundInventory();

}
