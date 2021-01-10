package com.example.hotelbooking.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "CUSTOMER")
@Cacheable(false)
public class CustomerInfo {

	@Id
	@NonNull
	@GeneratedValue
	@Column(name = "CUSTOMER_ID")
	private Integer Id;
	
	
	@NonNull
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@NonNull
	@Column(name = "CUSTOMER_BONUS_POINT")
	private Integer bonusPoint;

	@NonNull
	@Column(name = "CUSTOMER_BOOKING_STATUS")
	private String lastbookingStatus;

}
