package com.example.hotelbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerInput {

	private Integer customerId;

	private String roomType;

	private Integer roomCount;

}
