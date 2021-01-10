package com.example.hotelbooking.model;

import com.example.hotelbooking.utils.AllEnum.BookingStausEnum;
import com.example.hotelbooking.utils.AllEnum.HotelRoomType;

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
public class RoomStatusInfo {
	
	private HotelRoomType roomType;
	
	private BookingStausEnum bookingStatus;  

}
