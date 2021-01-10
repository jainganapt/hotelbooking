package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Customer;
import com.example.hotelbooking.model.UpdateCustomer;
import com.example.hotelbooking.utils.CustomerResponseInfo;
import com.example.hotelbooking.utils.ResponseInfo;

public interface CustData {

	CustomerResponseInfo addUser(Customer customer);

	ResponseInfo updateUser(UpdateCustomer updateCustomer);

}
