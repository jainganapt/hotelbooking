package com.example.hotelbooking.utils;

public class AllEnum {

	public enum StatusEnum {

		SUCCESS("SUCCESS"), FAIL("FAIL");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static StatusEnum forString(String text) {

			for (StatusEnum type : values()) {
				if (type.toString().equals(text)) {
					return type;
				}

			}
			return null;

		}

	}

	public enum BookingStausEnum {

		BOOKED("BOOKED"), PENDING_BOOKING("PENDING APPROVAL");

		private String value;

		BookingStausEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static BookingStausEnum forString(String text) {

			for (BookingStausEnum type : values()) {
				if (type.toString().equals(text)) {
					return type;
				}

			}
			return null;

		}

	}

	public enum HotelRoomType {

		SMALL("SMALL"), MEDIUM("MEDIUM"), BIG("BIG");

		private String value;

		HotelRoomType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static HotelRoomType forString(String text) {

			for (HotelRoomType type : values()) {
				if (type.toString().equals(text)) {
					return type;
				}

			}
			return null;

		}

	}

}
