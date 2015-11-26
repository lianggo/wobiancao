package com.wobiancao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wobiancao.entity.Coupon;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {

	Page<Coupon> findByMerchantId(Long merchantId, Pageable pageable);

//	List<Coupon> findByOrderByLikeCountDesc();
	
//	List<Coupon> findByCategoryIdOrderByLikeCountDesc(Long categoryId);
	
	List<Coupon> findByOrderByGetCountDesc();
	
	Page<Coupon> findByCategoryId(Long categoryId, Pageable pageable);
	
	List<Coupon> findByCategoryIdOrderByGetCountDesc(Long categoryId);
	
	List<Coupon> findByCategoryIdAndTagLikeOrderByGetCountDesc(Long categoryId, String tag);
	
	List<Coupon> findByTimeBeginLessThanAndTimeEndGreaterThanOrderByGetCountDesc(Date begin, Date end);
	
	List<Coupon> findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryIdOrderByGetCountDesc(Date begin, Date end, Long categoryId);
	
	List<Coupon> findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryIdAndTagLikeOrderByGetCountDesc(Date begin, Date end, Long categoryId, String tag);
	
	Page<Coupon> findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryId(Date begin, Date end, Long categoryId, Pageable pageable);
	
	Page<Coupon> findByTimeBeginLessThanAndTimeEndGreaterThan(Date begin, Date end, Pageable pageable);
	
}
