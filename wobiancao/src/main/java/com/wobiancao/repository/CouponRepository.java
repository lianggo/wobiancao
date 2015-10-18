package com.wobiancao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wobiancao.entity.Coupon;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

	Page<Coupon> findByMerchantId(Long merchantId, Pageable pageable);

	List<Coupon> findByOrderByLikeCountDesc();
	
	List<Coupon> findByCategoryIdOrderByLikeCountDesc(Long categoryId);
	
}
