package com.wobiancao.protocol.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "openid", "nickname", "sex", "province", "city", "country", "headimgurl", "privilege", "unionid" })
public class UserinfoSuccessResponse {

	@JsonProperty("openid")
	private String openId;

	private String nickname;

	private String sex;

	private String province;

	private String city;

	private String country;

	@JsonProperty("headimgurl")
	private String avatar;

	private List<String> privilege;

	@JsonProperty("unionid")
	private String unionId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
