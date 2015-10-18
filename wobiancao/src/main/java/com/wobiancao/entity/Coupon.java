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
@Table(name = "t_coupon")
public class Coupon extends AbstractEntity {

	@Column
	private String name;

	@Column
	private String slogan;

	@Column
	private String description;

	@Column
	private Long categoryId;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private StatusType status;

	public enum StatusType {
		DISABLED, ENABLED
	}

	@Column
	private Integer likeCount;
	
	@Column
	private String thirdUrl;

	@Column
	private Date timeBegin;

	@Column
	private Date timeEnd;

	@ManyToOne
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

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

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
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

}
