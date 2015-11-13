package com.wobiancao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coupons_category")
public class CouponCategory extends AbstractEntity {

	@Column(name = "name_cn")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "pic_url")
	private String picUrl;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
