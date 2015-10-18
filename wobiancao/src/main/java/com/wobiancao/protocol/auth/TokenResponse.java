package com.wobiancao.protocol.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

	@JsonProperty("errcode")
	private Integer errorCode;

	@JsonProperty("errmsg")
	private String errorMessage;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_in")
	private long expiresIn;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonProperty("openid")
	private String openId;

	private String scope;

	@JsonProperty("unionid")
	private String unionId;

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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
