package com.eric.ecommerce.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Product_Id")
	private long productId;
	@Column(name="Product_Name",nullable = false,length = 100)
	private String name;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="DOP")
	private LocalDate dop;
	@Column(name="Cost")
	private long cost;
	@Enumerated(EnumType.STRING)
	@Column(name="Product_Type")
	private ProductType productType;	

}
