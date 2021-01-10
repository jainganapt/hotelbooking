package com.example.hotelbooking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hotelbooking.entity.CustomerInfo;
import com.example.hotelbooking.exception.HotelBookingException;
import com.example.hotelbooking.model.Customer;
import com.example.hotelbooking.repo.CustomerRepository;
import com.example.hotelbooking.utils.CustomerResponseInfo;
import com.example.hotelbooking.utils.NewResponseInfo;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerInfoImplTest {

	@InjectMocks
	private CustomerInfoImpl customerInfoImpl;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private NewResponseInfo newResponseInfo;

	@Test(expected = HotelBookingException.class)
	public void saveUserExcpetion() {

		Optional<CustomerInfo> dbInfo = Optional.empty();
		//Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(dbInfo);
		customerInfoImpl.addUser(null);
	}
	
	@Test
	public void saveUser() {

		CustomerInfo c2 = new CustomerInfo();
		c2.setId(1);
		c2.setBonusPoint(100);
		c2.setCustomerName("hee");
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(c2);
		Customer c1  = new Customer();
		c1.setBonusPoint(100);
		c1.setName("hello");
		CustomerResponseInfo res =customerInfoImpl.addUser(c1);
		assertEquals(1, res.getCustomerId());
	}
}
