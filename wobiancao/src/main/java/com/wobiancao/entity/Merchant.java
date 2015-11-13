package com.wobiancao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Merchant extends AbstractEntity {

	@Column
	private String name;

	@Column(name = "desp")
	private String description;

	@Column(name = "pic_url")
	private String logo;

//	@Column
//	@Enumerated(EnumType.ORDINAL)
//	private StatusType status;
//
//	public enum StatusType {
//		DISABLED, ENABLED
//	}

	@OneToMany(mappedBy = "merchant")
	private List<Coupon> coupons;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}


}
