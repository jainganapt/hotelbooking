package com.example.hotelbooking.service;

import com.example.hotelbooking.model.CustomerInput;
import com.example.hotelbooking.utils.RoomResponseInfo;

public interface RoomBooking {

	RoomResponseInfo bookingRoom(CustomerInput customerInput);
}
