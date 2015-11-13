package com.wobiancao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Customer extends AbstractEntity {

	@Column(name = "open_id")
	private String openId;

	@Column(name = "username")
	private String nickname;

	@Column(name = "users_pic_url")
	private String avatar;
	
	@Column(name = "remote_system")
	private String remoteSystem;
	
	@Column(name = "user_info_remote")
	private String userinfo;
	
	@Column(name = "create_datetime")
	private Date createDate;

	@Column(name = "last_logged_in")
	private Date lastLoginDate;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_follow_shop", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "shop_id", referencedColumnName = "id") })
	private List<Merchant> merchantsFollowed;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_like_coupon", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "coupon_id", referencedColumnName = "id") })
	private List<Coupon> couponsLiked;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_has_coupon", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "coupon_id", referencedColumnName = "id") })
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRemoteSystem() {
		return remoteSystem;
	}

	public void setRemoteSystem(String remoteSystem) {
		this.remoteSystem = remoteSystem;
	}

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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
