package com.wobiancao.protocol.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "errcode", "errmsg" })
public class AuthFailResponse {

	@JsonProperty("errcode")
	private Integer errorCode;

	@JsonProperty("errmsg")
	private String errorMessage;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
