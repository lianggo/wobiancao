package com.wobiancao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon extends AbstractEntity {

	@Column
	private String name;

	@Column
	private String slogan;

	@Column(name = "desp")
	private String description;

	@Column(name = "coupon_category")
	private Long categoryId;

//	@Column
//	@Enumerated(EnumType.ORDINAL)
//	private StatusType status;
//
//	public enum StatusType {
//		DISABLED, ENABLED
//	}

//	@Column(name = "like")
//	private Integer likeCount = 0;
	
	@Column(name = "has_count")
	private Integer getCount = 0;
	
	@Column(name = "third_url", length = 1000)
	private String thirdUrl;

	@Column(name = "datetime_begin")
	private Date timeBegin;

	@Column(name = "datetime_end")
	private Date timeEnd;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Merchant merchant;
	
	@Column
	private String tag;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

//	public StatusType getStatus() {
//		return status;
//	}
//
//	public void setStatus(StatusType status) {
//		this.status = status;
//	}

//	public Integer getLikeCount() {
//		return likeCount;
//	}
//
//	public void setLikeCount(Integer likeCount) {
//		this.likeCount = likeCount;
//	}

	public Integer getGetCount() {
		return getCount;
	}

	public void setGetCount(Integer getCount) {
		this.getCount = getCount;
	}

	public String getThirdUrl() {
		return thirdUrl;
	}

	public void setThirdUrl(String thirdUrl) {
		this.thirdUrl = thirdUrl;
	}

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
