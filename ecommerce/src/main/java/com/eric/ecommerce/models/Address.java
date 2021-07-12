package com.eric.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {
	@Column(name="Door_No")
	private String doorNo;
	@Column(name="Street_Name")
	private String streetName;
	@Column(name="City")
	private String city;
	@Column(name="State")
	private String state;
}
