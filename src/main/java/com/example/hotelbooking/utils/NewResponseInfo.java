package com.example.hotelbooking.utils;

import java.time.OffsetDateTime;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.hotelbooking.utils.AllEnum.StatusEnum;

@Component
public class NewResponseInfo {

	private static final String LEGACY_TRACE_ID_NAME = "X-B3-TraceId";

	public ResponseInfo getNewResponseInfo(String errorCode, String DescriptionInEnglish, String status) {

		String uri = null;
		try {
			uri = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
		} catch (Exception ex) {
			// log.debug(ex.getMessage());
		}

		StatusEnum flag = null;
		if ("SUCCESS".equals(status)) {
			flag = StatusEnum.SUCCESS;
		} else {
			flag = StatusEnum.FAIL;
		}

		ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setInnerCode(errorCode);
		responseInfo.setDescription(DescriptionInEnglish);
		responseInfo.setStatus(flag);
		responseInfo.setTimestamp(OffsetDateTime.now());
		responseInfo.setTraceID(MDC.get(LEGACY_TRACE_ID_NAME));
		responseInfo.setRequestUri(uri);
		return responseInfo;
	}
}
