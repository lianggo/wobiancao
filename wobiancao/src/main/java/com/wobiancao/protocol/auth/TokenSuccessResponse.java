package com.wobiancao.protocol.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "access_token", "expires_in", "refresh_token", "openid", "scope", "unionid" })
public class TokenSuccessResponse {

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
