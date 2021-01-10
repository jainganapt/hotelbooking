package com.example.hotelbooking.exception;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.hotelbooking.utils.AllEnum.StatusEnum;
import com.example.hotelbooking.utils.ErrorCodeDesc;
import com.example.hotelbooking.utils.ResponseInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustResponseEntityHandler extends ResponseEntityExceptionHandler {

	private static final String LEGACY_TRACE_ID_NAME = "X-B3-TraceId";
	private String requestUri;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		requestUri = request.getDescription(false);
		ResponseInfo responseInfo = getResponseInfo(ErrorCodeDesc.DEFAULT, ErrorCodeDesc.DEFAULT_EN, ex, null);
		printException(ex, null);

		return new ResponseEntity<Object>(responseInfo, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HotelBookingException.class)
	public final ResponseEntity<Object> HotelBookingExcetion(HotelBookingException ex, WebRequest request) {

		String errorCode = ex.getErrorCode();
		String additionalDescription = ex.additionalDescription;
		Exception originalException = ex.getOriginalException();
		HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;
		ResponseInfo responseInfo = null;

		this.requestUri = request.getDescription(false);

		if (errorCode == null) {
			responseInfo = getResponseInfo(ErrorCodeDesc.DEFAULT, ErrorCodeDesc.DEFAULT_EN, originalException,
					additionalDescription);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			printException(originalException, ex);
			return new ResponseEntity<Object>(responseInfo, httpStatus);
		}

		switch (errorCode) {

		case ErrorCodeDesc.ROOM_TYPE_NOT_FOUND:
			responseInfo = getResponseInfo(ErrorCodeDesc.ROOM_TYPE_NOT_FOUND, ErrorCodeDesc.ROOM_TYPE_NOT_FOUND_EN,
					originalException, additionalDescription);
			httpStatus = HttpStatus.BAD_REQUEST;
			break;

		case ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID:
			responseInfo = getResponseInfo(ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID,
					ErrorCodeDesc.CUSTOMER_NOT_FOUND_BY_ID_EN, originalException, additionalDescription);
			httpStatus = HttpStatus.BAD_REQUEST;
			break;

		case ErrorCodeDesc.CUSTOMER_INPUT_DATA_IS_EMPTY:
			responseInfo = getResponseInfo(ErrorCodeDesc.CUSTOMER_INPUT_DATA_IS_EMPTY,
					ErrorCodeDesc.CUSTOMER_INPUT_DATA_IS_EMPTY_EN, originalException, additionalDescription);
			httpStatus = HttpStatus.BAD_REQUEST;
			break;
			
		case ErrorCodeDesc.CUSTOMER_UPDATED_FAIED_ID:
			responseInfo = getResponseInfo(ErrorCodeDesc.CUSTOMER_UPDATED_FAIED_ID,
					ErrorCodeDesc.CUSTOMER_UPDATED_FAIED_ID_EN, originalException, additionalDescription);
			httpStatus = HttpStatus.BAD_REQUEST;
			break;

		default:
			responseInfo = getResponseInfo(ErrorCodeDesc.DEFAULT, ErrorCodeDesc.DEFAULT_EN, originalException,
					additionalDescription);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			break;

		}

		printException(originalException, ex);

		return new ResponseEntity<Object>(responseInfo, httpStatus);

	}

	// -----------------------------------------------------------------

	private void printException(Exception ex, HotelBookingException ex2) {

		if (ex != null) {
			log.info("--EXCEPTION-- :");
			log.info("***************************");
			log.info("Exception : ", ex);
			if (ex.getMessage() != null) {
				log.info("Message : " + ex.getMessage());
			}
			log.info("----------------------------");

		}
		if (ex2 != null) {
			log.info("--CUSTOM EXCEPTION-- :");
			log.info("*****************************");
			log.info("CustException : ", ex2);
			if (ex2.getMessage() != null) {
				log.info("Message : " + ex2.getMessage());
			}
			if (ex2.getErrorCode() != null) {
				log.info("Error code : " + ex2.getErrorCode());
			}
			if (ex2.getAdditionalDescription() != null) {
				log.info("Error code : " + ex2.getAdditionalDescription());
			}
			log.info("------------------------------");
		}
	}

	public ResponseInfo getResponseInfo(String errorCodeDescC, String errorCodeDescE, Exception ex,
			String additionalDescription) {

		ResponseInfo responseInfo = new ResponseInfo();

		responseInfo.setInnerCode(errorCodeDescC);
		responseInfo.setDescription(errorCodeDescE);
		responseInfo.setStatus(StatusEnum.FAIL);
		responseInfo.setTimestamp(OffsetDateTime.now());
		responseInfo.setTraceID(MDC.get(LEGACY_TRACE_ID_NAME));

		if ((additionalDescription == null || additionalDescription.isEmpty()) && ex != null
				&& ex.getMessage() != null) {
			responseInfo.setDefaultMessage(ex.getMessage());

		} else {
			responseInfo.setDefaultMessage(additionalDescription);
		}

		if (ex != null && ex.getStackTrace() != null) {
			responseInfo.setStackTrace(Arrays.toString(ex.getStackTrace()));
		}

		responseInfo.setRequestUri(requestUri);

		return responseInfo;

	}

}
