package com.eric.ecommerce.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeliverySchedule {

	private long productId;
	private LocalDate requestedDate;
	private LocalDate plannedDeliveryDate;
	private long availableQty;
	
}
