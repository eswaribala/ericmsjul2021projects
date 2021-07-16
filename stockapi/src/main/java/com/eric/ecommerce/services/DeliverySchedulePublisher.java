package com.eric.ecommerce.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.eric.ecommerce.models.DeliverySchedule;
import com.eric.ecommerce.models.Stock;
import com.eric.ecommerce.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeliverySchedulePublisher {
    @Autowired
	private StockRepository stockRepository;
    
    private boolean status;
  //1. General topic with string payload
	
  	@Value(value = "${stock.topic.name}")
      private String stockTopicName;
  	
  	@Autowired
      private KafkaTemplate<String, DeliverySchedule> kafkaTemplate;
  	
  	public void sendMessage(long productId) 
	{
		DeliverySchedule deliverySchedule=new DeliverySchedule();
		deliverySchedule.setRequestedDate(LocalDate.now());
		List<Stock> stockStatus=stockRepository.findAll();
		Stock stockStatusHistory=stockStatus.stream()
		.sorted((s1,s2)->s1.getProduct().getDop().compareTo(s2.getProduct().getDop()))
		.filter(s3->s3.getProduct().getProductId()==productId)
		.findFirst().orElse(null);
		if(stockStatusHistory!=null)
		{
			deliverySchedule.setProductId(stockStatusHistory.getProduct().getProductId());
			deliverySchedule.setAvailableQty(stockStatusHistory.getQty());
			deliverySchedule.setPlannedDeliveryDate(LocalDate.now().plusWeeks(2));
		}		
		
		ListenableFuture<SendResult<String, DeliverySchedule>> future 
			= this.kafkaTemplate.send(stockTopicName, deliverySchedule);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, DeliverySchedule>>() {
            @Override
            public void onSuccess(SendResult<String, DeliverySchedule> result) {
            	log.info("Sent message: " + deliverySchedule.getPlannedDeliveryDate().toString() 
            			+ " with offset: " + result.getRecordMetadata().offset());
            	System.out.println("Sent message: " + deliverySchedule.getPlannedDeliveryDate().toString() 
            			+ " with offset: " + result.getRecordMetadata().offset());
           // status=true;
            }
            

            @Override
            public void onFailure(Throwable ex) {
            	log.error("Unable to send delivery Date for Product : " + deliverySchedule.getProductId(), ex);
              // status=false;
            }
       });
		
	}


}
