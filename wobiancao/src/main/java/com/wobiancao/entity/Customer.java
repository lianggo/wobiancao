package com.wobiancao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer")
public class Customer extends AbstractEntity {

	@Column
	private String openId;

	@Column
	private String nickname;

	@Column
	private String sex;

	@Column
	private String country;

	@Column
	private String province;

	@Column
	private String city;

	@Column
	private String avatar;

	@Column
	private String accessToken;

	@Column
	private String refreshToken;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_customer_merchant_follow", joinColumns = { @JoinColumn(name = "customer_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "merchant_id", referencedColumnName = "id") })
	private List<Merchant> merchantsFollowed;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_customer_coupon_like", joinColumns = { @JoinColumn(name = "customer_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "coupon_id", referencedColumnName = "id") })
	private List<Coupon> couponsLiked;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_customer_coupon_get", joinColumns = { @JoinColumn(name = "customer_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "coupon_id", referencedColumnName = "id") })
	private List<Coupon> couponsGot;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<Merchant> getMerchantsFollowed() {
		return merchantsFollowed;
	}

	public void setMerchantsFollowed(List<Merchant> merchantsFollowed) {
		this.merchantsFollowed = merchantsFollowed;
	}

	public List<Coupon> getCouponsLiked() {
		return couponsLiked;
	}

	public void setCouponsLiked(List<Coupon> couponsLiked) {
		this.couponsLiked = couponsLiked;
	}

	public List<Coupon> getCouponsGot() {
		return couponsGot;
	}

	public void setCouponsGot(List<Coupon> couponsGot) {
		this.couponsGot = couponsGot;
	}

}
