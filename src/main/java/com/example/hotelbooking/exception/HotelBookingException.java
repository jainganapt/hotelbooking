package com.example.hotelbooking.exception;

import lombok.Data;

@Data
public class HotelBookingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected Exception originalException;

	protected String errorCode;

	protected String additionalDescription;

	public HotelBookingException(String errorCode, String additionalDescription, Exception originalException) {
		super();
		this.errorCode = errorCode;
		this.additionalDescription = additionalDescription;
		this.originalException = originalException;
	}

	public HotelBookingException(String errorCode, String additionalDescription) {
		this(errorCode, additionalDescription, null);
	}

	public HotelBookingException(String errorCode, Exception originalException) {
		this(errorCode, null, originalException);
	}

	public HotelBookingException(String errorCode) {
		this(errorCode, null, null);

	}
}
