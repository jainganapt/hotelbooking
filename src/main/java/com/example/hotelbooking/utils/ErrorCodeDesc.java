package com.example.hotelbooking.utils;

public class ErrorCodeDesc {

	// Default Errors------------------------------------------------------------
	public static final String DEFAULT = "90000";
	public static final String DEFAULT_EN = "Incorrect Request or Exception occurred, check logs for more details";

	// MS Error------------------------------------------------------------------

	public static final String ROOM_TYPE_NOT_FOUND = "70000";
	public static final String ROOM_TYPE_NOT_FOUND_EN = "No product found";

	public static final String CUSTOMER_NOT_FOUND_BY_ID = "70001";
	public static final String CUSTOMER_NOT_FOUND_BY_ID_EN = "No customer found with this id";
	
	public static final String CUSTOMER_INPUT_DATA_IS_EMPTY = "70002";
	public static final String CUSTOMER_INPUT_DATA_IS_EMPTY_EN = "input customer data not found with this id";

	public static final String CUSTOMER_UPDATED_FAIED_ID = "70003";
	public static final String CUSTOMER_UPDATED_FAIED_ID_EN = " customer updated failed ";

	// SUCCESS MSG --------------------------------------------------------------
	public static final String SUCCESS = "20001";
	public static final String SUCCESS_EN = "SUCCESS";

}
