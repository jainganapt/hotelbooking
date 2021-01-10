package com.example.hotelbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotelbooking.entity.CustomerInfo;
import com.example.hotelbooking.exception.HotelBookingException;
import com.example.hotelbooking.model.Customer;
import com.example.hotelbooking.model.UpdateCustomer;
import com.example.hotelbooking.repo.CustomerRepository;
import com.example.hotelbooking.utils.AllEnum.BookingStausEnum;
import com.example.hotelbooking.utils.AllEnum.StatusEnum;
import com.example.hotelbooking.utils.CustomerResponseInfo;
import com.example.hotelbooking.utils.ErrorCodeDesc;
import com.example.hotelbooking.utils.NewResponseInfo;
import com.example.hotelbooking.utils.ResponseInfo;

@Service
public class CustomerInfoImpl implements CustData {

	@Autowired
	private NewResponseInfo newResponseInfo;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerResponseInfo addUser(Customer customer) {

		CustomerResponseInfo CustomerResponseInfo = new CustomerResponseInfo();

		CustomerInfo c1 = new CustomerInfo();
		if (null == customer || null == customer.getBonusPoint() || null == customer.getName()) {
			throw new HotelBookingException(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID, "user not found");
		}
		c1.setBonusPoint(customer.getBonusPoint());
		c1.setCustomerName(customer.getName());
		c1.setLastbookingStatus(BookingStausEnum.PENDING_BOOKING.toString());
		ResponseInfo res = newResponseInfo.getNewResponseInfo(ErrorCodeDesc.SUCCESS, "User data is there",
				StatusEnum.SUCCESS.toString());

		CustomerInfo s1 = customerRepository.save(c1);

		CustomerResponseInfo.setNewResponseInfo(res);
		CustomerResponseInfo.setCustomerId(s1.getId());

		return CustomerResponseInfo;

	}

	@Override
	public ResponseInfo updateUser(UpdateCustomer updateCustomer) {

		Integer status = customerRepository.updateById(updateCustomer.getCustomerId(), updateCustomer.getBonusPoint());

		if (status == 0) {
			throw new HotelBookingException(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID, "user not found");

		}

		return newResponseInfo.getNewResponseInfo(ErrorCodeDesc.SUCCESS, "User data is there",
				StatusEnum.SUCCESS.toString());

	}

}
