package com.example.hotelbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelbooking.model.Customer;
import com.example.hotelbooking.model.CustomerInput;
import com.example.hotelbooking.model.UpdateCustomer;
import com.example.hotelbooking.service.CustData;
import com.example.hotelbooking.service.RoomBooking;
import com.example.hotelbooking.utils.CustomerResponseInfo;
import com.example.hotelbooking.utils.ResponseInfo;
import com.example.hotelbooking.utils.RoomResponseInfo;

@RestController
public class CustomerDataController {

	@Autowired
	private CustData custData;

	@Autowired
	private RoomBooking roomBooking;

	@PostMapping("/addcustinfo")
	public CustomerResponseInfo adduser(@RequestBody Customer customer) {

		return custData.addUser(customer);

	}

	@PatchMapping("/updatecustinfo")
	public ResponseInfo updateuser(@RequestBody UpdateCustomer updateCustomer) {

		return custData.updateUser(updateCustomer);

	}

	@PostMapping("/roombooking")
	public RoomResponseInfo bookroom(@RequestBody CustomerInput customerInput) {

		return roomBooking.bookingRoom(customerInput);

	}
}
