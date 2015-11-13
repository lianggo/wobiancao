package com.wobiancao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wobiancao.entity.Coupon;
import com.wobiancao.entity.CouponCategory;
import com.wobiancao.entity.Customer;
import com.wobiancao.repository.CouponCategoryRepository;
import com.wobiancao.repository.CouponRepository;
import com.wobiancao.repository.CustomerRepository;
import com.wobiancao.utils.StringUtils;

@Controller
@RequestMapping(value = "/coupon")
public class CouponController {

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponCategoryRepository couponCategoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String tag, HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId()); // 从数据库中取得最新的用户信息
		
//		Map<Long, Coupon> couponsLikedMap = new HashMap<Long, Coupon>();
//		for (Coupon c : customer.getCouponsLiked()) {
//			couponsLikedMap.put(c.getId(), c);
//		}
//		model.addAttribute("couponsLikedMap", couponsLikedMap); // 当前用户喜欢的优惠券
		Map<Long, Coupon> couponsGotMap = new HashMap<Long, Coupon>();
		for (Coupon c : customer.getCouponsGot()) {
			couponsGotMap.put(c.getId(), c);
		}
		model.addAttribute("couponsGotMap", couponsGotMap); // 当前用户领过的优惠券
		
		List<Coupon> coupons = null;
		Date now = new Date();
		if (categoryId != null) {
			if (tag != null) {
				coupons = couponRepository.findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryIdAndTagLikeOrderByGetCountDesc(now, now, categoryId, "%" + tag + "%");
				model.addAttribute("currentTag", tag);
			} else {
				coupons = couponRepository.findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryIdOrderByGetCountDesc(now, now, categoryId);
			}
			// 生成二级Tag
			List<Coupon> categoryCoupons = couponRepository.findByTimeBeginLessThanAndTimeEndGreaterThanAndCategoryIdOrderByGetCountDesc(now, now, categoryId);
			List<String> tags = new ArrayList<String>();
			for (Coupon c : categoryCoupons) {
				String couponTags = c.getTag();
				if (couponTags == null) {
					couponTags = "";
				}
				String tagArray[] = couponTags.split(";");
				for (String t : tagArray) {
					if (StringUtils.hasText(t) && !tags.contains(t)) {
						tags.add(t);
					}
				}
			}
			model.addAttribute("tags", tags);
			model.addAttribute("currentCategoryId", categoryId);
		} else {
			coupons = couponRepository.findByTimeBeginLessThanAndTimeEndGreaterThanOrderByGetCountDesc(now, now);
		}
		model.addAttribute("coupons", coupons);
		
		Iterable<CouponCategory> couponCategories = couponCategoryRepository.findAll();
		model.addAttribute("couponCategories", couponCategories);
		return "coupon/coupon_index2";
	}
	
//	@RequestMapping(value = "/like")
//	@ResponseBody
//	public String likeCoupon(@RequestParam Long id, HttpSession session) {
//		Customer customer = (Customer) session.getAttribute("customer");
//		customer = customerRepository.findOne(customer.getId());
//		Coupon coupon = couponRepository.findOne(id);
//		List<Coupon> couponLiked = customer.getCouponsLiked();
//		if (!couponLiked.contains(coupon)) {
//			couponLiked.add(coupon);
//			coupon.setLikeCount(coupon.getLikeCount() + 1);
//			customerRepository.save(customer);
//			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
//		} else {
//			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
//		}
//	}
	
//	@RequestMapping(value = "/unlike")
//	@ResponseBody
//	public String unlikeCoupon(@RequestParam Long id, HttpSession session) {
//		Customer customer = (Customer) session.getAttribute("customer");
//		customer = customerRepository.findOne(customer.getId());
//		Coupon coupon = couponRepository.findOne(id);
//		List<Coupon> couponLiked = customer.getCouponsLiked();
//		if (couponLiked.contains(coupon)) {
//			couponLiked.remove(coupon);
//			coupon.setLikeCount(coupon.getLikeCount() - 1);
//			customerRepository.save(customer);
//			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
//		} else {
//			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
//		}
//	}
	
	@RequestMapping(value = "/get")
	public String getCoupon(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		System.out.println("====session customer id=" + customer.getId());
		customer = customerRepository.findOne(customer.getId());
		Coupon coupon = couponRepository.findOne(id);
		List<Coupon> couponGot = customer.getCouponsGot();
		System.out.println("====couponGot:");
		for (Coupon c : couponGot) {
			System.out.println(c.getId());
		}
		System.out.println("====currentCoupon:" + id);
		if (!couponGot.contains(coupon)) {
			System.out.println("not contain");
			couponGot.add(coupon);
			customerRepository.save(customer);
			session.setAttribute("customer", customer);
			coupon.setGetCount(coupon.getGetCount() + 1);
			couponRepository.save(coupon);
		}
		
		return "redirect:" + coupon.getThirdUrl();
	}

}
