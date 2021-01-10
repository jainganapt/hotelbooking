package com.example.hotelbooking.utils;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.example.hotelbooking.utils.AllEnum.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class ResponseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("status")
	private StatusEnum status = null;

	@JsonProperty("innerCode")
	private String innerCode = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("defaultMessage")
	private String defaultMessage = null;

	@JsonProperty("stackTrace")
	private String stackTrace = null;

	@JsonProperty("traceID")
	private String traceID = null;

	@JsonProperty("timestamp")
	private OffsetDateTime timestamp = null;

	@JsonProperty("requestUri")
	private String requestUri = null;

	public ResponseInfo status(StatusEnum status) {
		this.status = status;
		return this;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum fail) {
		this.status = fail;
	}

	public ResponseInfo innerCode(String innerCode) {
		this.innerCode = innerCode;
		return this;
	}

	/**
	 * Code for each response
	 * 
	 * @return innerCode
	 **/
	@ApiModelProperty(value = "Code for each response")

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public ResponseInfo description(String description) {
		this.description = description;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResponseInfo defaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
		return this;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public ResponseInfo stackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
		return this;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public ResponseInfo traceID(String traceID) {
		this.traceID = traceID;
		return this;
	}

	@ApiModelProperty(value = "")

	public String getTraceID() {
		return traceID;
	}

	public void setTraceID(String traceID) {
		this.traceID = traceID;
	}

	public ResponseInfo timestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(OffsetDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ResponseInfo requestUri(String requestUri) {
		this.requestUri = requestUri;
		return this;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResponseInfo responseInfo = (ResponseInfo) o;
		return Objects.equals(this.status, responseInfo.status)
				&& Objects.equals(this.innerCode, responseInfo.innerCode)
				&& Objects.equals(this.description, responseInfo.description)
				&& Objects.equals(this.defaultMessage, responseInfo.defaultMessage)
				&& Objects.equals(this.stackTrace, responseInfo.stackTrace)
				&& Objects.equals(this.traceID, responseInfo.traceID)
				&& Objects.equals(this.timestamp, responseInfo.timestamp)
				&& Objects.equals(this.requestUri, responseInfo.requestUri);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, innerCode, description, defaultMessage, stackTrace, traceID, timestamp, requestUri);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResponseInfo {\n");

		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    innerCode: ").append(toIndentedString(innerCode)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    defaultMessage: ").append(toIndentedString(defaultMessage)).append("\n");
		sb.append("    stackTrace: ").append(toIndentedString(stackTrace)).append("\n");
		sb.append("    traceID: ").append(toIndentedString(traceID)).append("\n");
		sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
		sb.append("    requestUri: ").append(toIndentedString(requestUri)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
