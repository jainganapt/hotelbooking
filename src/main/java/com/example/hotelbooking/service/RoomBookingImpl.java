package com.example.hotelbooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotelbooking.entity.CustomerInfo;
import com.example.hotelbooking.entity.HotelRoom;
import com.example.hotelbooking.exception.HotelBookingException;
import com.example.hotelbooking.model.CustomerInput;
import com.example.hotelbooking.model.RoomStatusInfo;
import com.example.hotelbooking.repo.CustomerRepository;
import com.example.hotelbooking.repo.HotelRoomRepository;
import com.example.hotelbooking.utils.AllEnum.BookingStausEnum;
import com.example.hotelbooking.utils.AllEnum.HotelRoomType;
import com.example.hotelbooking.utils.AllEnum.StatusEnum;
import com.example.hotelbooking.utils.ErrorCodeDesc;
import com.example.hotelbooking.utils.NewResponseInfo;
import com.example.hotelbooking.utils.ResponseInfo;
import com.example.hotelbooking.utils.RoomResponseInfo;

@Service
public class RoomBookingImpl implements RoomBooking {

	@Autowired
	private NewResponseInfo newResponseInfo;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Override
	public RoomResponseInfo bookingRoom(CustomerInput customerInput) {

		RoomResponseInfo roomResponseInfo = new RoomResponseInfo();

		ResponseInfo res = newResponseInfo.getNewResponseInfo(ErrorCodeDesc.SUCCESS, "User room booking service",
				StatusEnum.SUCCESS.toString());

		RoomStatusInfo roomStatusInfo = new RoomStatusInfo();
		if (null != customerInput.getRoomType()
				&& HotelRoomType.forString(customerInput.getRoomType().toUpperCase()) != null) {
			roomStatusInfo.setRoomType(HotelRoomType.forString(customerInput.getRoomType().toUpperCase()));
		} else {

			throw new HotelBookingException(ErrorCodeDesc.ROOM_TYPE_NOT_FOUND, "room type not found");
		}

		HotelRoom hotelRoom = hotelRoomRepository.findByRoomType(roomStatusInfo.getRoomType().toString());

		Optional<CustomerInfo> customerInfo = customerRepository.findById(customerInput.getCustomerId());

		if (!customerInfo.isPresent()) {

			throw new HotelBookingException(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID, "customrt not found");
		}

		if ((customerInput.getRoomCount() * hotelRoom.getRoomPrice()) <= customerInfo.get().getBonusPoint()) {

			roomStatusInfo.setBookingStatus(BookingStausEnum.BOOKED);
			customerInfo.get().setBonusPoint(
					customerInfo.get().getBonusPoint() - (customerInput.getRoomCount() * hotelRoom.getRoomPrice()));
			Integer status = customerRepository.updateByIdforStatusBonus(customerInfo.get().getId(),
					customerInfo.get().getBonusPoint(), BookingStausEnum.BOOKED.toString());

			if (status == 0) {
				throw new HotelBookingException(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID, "user not found");

			}

		} else {
			Integer status = customerRepository.updateByIdforStatus(customerInfo.get().getId(),
					BookingStausEnum.PENDING_BOOKING.toString());

			if (status == 0) {
				throw new HotelBookingException(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID, "user not found");

			}
			roomStatusInfo.setBookingStatus(BookingStausEnum.PENDING_BOOKING);

		}
		roomResponseInfo.setNewResponseInfo(res);
		roomResponseInfo.setRoomStatusInfo(roomStatusInfo);
		return roomResponseInfo;
	}

}
