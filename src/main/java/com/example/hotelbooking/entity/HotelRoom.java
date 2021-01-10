package com.example.hotelbooking.entity;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="ROOM")
@Cacheable(false)
public class HotelRoom {

	@Id
	@NonNull
	@GeneratedValue
	@Column(name="ROOM_ID")
	private Integer roomId;
	
	@Column(name="ROOM_TYPE")
	private String roomType;
	
	@Column(name="ROOM_PRICE")
	private Integer roomPrice;
}

