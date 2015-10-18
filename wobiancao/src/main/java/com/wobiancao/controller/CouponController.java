package com.wobiancao.controller;

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
	public String index(@RequestParam(required = false) Long categoryId, HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId()); // 从数据库中取得最新的用户信息
		
		Map<Long, Coupon> couponsLikedMap = new HashMap<Long, Coupon>();
		for (Coupon c : customer.getCouponsLiked()) {
			couponsLikedMap.put(c.getId(), c);
		}
		model.addAttribute("couponsLikedMap", couponsLikedMap); // 当前用户喜欢的优惠券
		
		List<Coupon> coupons = null;
		if (categoryId != null) {
			coupons = couponRepository.findByCategoryIdOrderByLikeCountDesc(categoryId);
		} else {
			coupons = couponRepository.findByOrderByLikeCountDesc();
		}
		model.addAttribute("coupons", coupons);
		
		Iterable<CouponCategory> couponCategories = couponCategoryRepository.findAll();
		model.addAttribute("couponCategories", couponCategories);
		return "coupon/coupon_index";
	}
	
	@RequestMapping(value = "/like")
	@ResponseBody
	public String likeCoupon(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerRepository.findOne(customer.getId());
		Coupon coupon = couponRepository.findOne(id);
		List<Coupon> couponLiked = customer.getCouponsLiked();
		if (!couponLiked.contains(coupon)) {
			couponLiked.add(coupon);
			coupon.setLikeCount(coupon.getLikeCount() + 1);
			customerRepository.save(customer);
			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
		} else {
			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
		}
	}
	
	@RequestMapping(value = "/unlike")
	@ResponseBody
	public String unlikeCoupon(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerRepository.findOne(customer.getId());
		Coupon coupon = couponRepository.findOne(id);
		List<Coupon> couponLiked = customer.getCouponsLiked();
		if (couponLiked.contains(coupon)) {
			couponLiked.remove(coupon);
			coupon.setLikeCount(coupon.getLikeCount() - 1);
			customerRepository.save(customer);
			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
		} else {
			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
		}
	}
	
	@RequestMapping(value = "/get")
	public String getCoupon(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerRepository.findOne(customer.getId());
		Coupon coupon = couponRepository.findOne(id);
		List<Coupon> couponGot = customer.getCouponsGot();
		if (!couponGot.contains(coupon)) {
			couponGot.add(coupon);
			customerRepository.save(customer);
		}
		return "redirect:" + coupon.getThirdUrl();
	}

}
