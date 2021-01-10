package com.example.hotelbooking.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j(topic = "HOTEL_BOOKING_REST_LOGGER")
public class ApiInputOutputLogger implements Filter {

	private static final String TRACE_ID_HEADER = "X-B3-TraceId";
	private static final List<String> URIS_TO_IGNORE = Arrays.asList("/swagger*/**", "/csrf", "/v2/api-docs/**",
			"/webjars/**/","/h2-console*/**/");


	private static final AntPathMatcher matcher = new AntPathMatcher();


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse
				&& URIS_TO_IGNORE.stream().noneMatch(
						path -> matcher.match(path, ((HttpServletRequest) servletRequest).getRequestURI()))) {

			String requestTraceId = MDC.get(TRACE_ID_HEADER);
			String requestURL = null;
			Map<String, String> requestHeaders = new HashMap<>();
			//JsonObject requestPayload = JsonObject.create();
			String responsePayload = null;
			//JsonObject responseHeaders = JsonObject.create();
			String requestMethod = null;
			int responseStatus = 0;
			String requestQueryString = null;

			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletRequest requestToCache = new ContentCachingRequestWrapper(request);
			ContentCachingRequestWrapper cachedRequest = WebUtils.getNativeRequest(requestToCache,
					ContentCachingRequestWrapper.class);

			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpServletResponse responseToCache = new ContentCachingResponseWrapper(response);
			ContentCachingResponseWrapper cachedResponse = WebUtils.getNativeResponse(responseToCache,
					ContentCachingResponseWrapper.class);

			if (cachedRequest != null) {

				requestURL = cachedRequest.getRequestURL().toString();
				requestMethod = cachedRequest.getMethod();
				requestQueryString = cachedRequest.getQueryString();
				requestHeaders = logRequestHeader(cachedRequest);

			}

			// Process Response
			chain.doFilter(cachedRequest, cachedResponse);

			String requestBody = this.getContentAsString(
					cachedRequest != null ? cachedRequest.getContentAsByteArray() : null,
					cachedRequest.getCharacterEncoding());
			//requestPayload.put("Request Body", requestBody);

			log.info(
					"REST Request: Trace ID: {}, Request URL: {}, HTTP Method: {}, Request Header: {},  Request Query String: {},  Request Payload: {}",
					requestTraceId, requestURL, requestMethod, requestHeaders, requestQueryString);

			responsePayload = extractResponsePayload(cachedResponse);
			responseStatus = cachedResponse.getStatus();

			log.info(
					"REST Response: Trace ID: {}, HTTP Response Status Code: {}, Response Headers: {}, Response Payload: {}",
					requestTraceId, responseStatus, responsePayload);

		} else {
			chain.doFilter(servletRequest, servletResponse);
		}

	}

	private String getContentAsString(byte[] buf, String charsetName) {
		if (buf == null || buf.length == 0)
			return "";

		try {
			return new String(buf, 0, buf.length, charsetName);
		} catch (UnsupportedEncodingException ex) {
			return "Unsupported Encoding";
		}
	}

	private String extractResponsePayload(ContentCachingResponseWrapper cachedResponse) {

		String responsePayload = "{}";

		if (cachedResponse != null) {
			byte[] buf = cachedResponse.getContentAsByteArray();

			if (buf.length > 0) {
				try {
					responsePayload = new String(buf, 0, buf.length);
					cachedResponse.copyBodyToResponse();
				} catch (IOException e1) {

				}
			}
		}

		return responsePayload;

	}

	private static Map<String, String> logRequestHeader(ContentCachingRequestWrapper request) {

		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);

		Map<String, String> requestHeaders = new HashMap<>();

		Enumeration<String> headerNames = wrapper.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			requestHeaders.put(headerName, wrapper.getHeader(headerName));
		}

		return requestHeaders;
	}
}