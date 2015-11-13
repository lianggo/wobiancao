package com.wobiancao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wobiancao.entity.Coupon;
import com.wobiancao.entity.Customer;
import com.wobiancao.entity.Merchant;
import com.wobiancao.repository.CustomerRepository;
import com.wobiancao.repository.MerchantRepository;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantController {

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "/{id}")
	public String details(@PathVariable Long id, HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId()); // 从数据库中取得最新的用户信息
		
		Merchant merchant = merchantRepository.findOne(id);
		boolean isFollowed = customer.getMerchantsFollowed().contains(merchant);
		model.addAttribute("merchant", merchant);
		model.addAttribute("isFollowed", isFollowed);
		
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
		
		return "merchant/merchant_details2";
	}
	
	@RequestMapping(value = "/follow")
	@ResponseBody
	public String followMerchant(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerRepository.findOne(customer.getId());
		Merchant merchant = merchantRepository.findOne(id);
		List<Merchant> merchantsFollowed = customer.getMerchantsFollowed();
		if (!merchantsFollowed.contains(merchant)) {
			merchantsFollowed.add(merchant);
			customerRepository.save(customer);
			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
		} else {
			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
		}
	}
	
	@RequestMapping(value = "/unfollow")
	@ResponseBody
	public String unfollowMerchant(@RequestParam Long id, HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerRepository.findOne(customer.getId());
		Merchant merchant = merchantRepository.findOne(id);
		List<Merchant> merchantsFollowed = customer.getMerchantsFollowed();
		if (merchantsFollowed.contains(merchant)) {
			merchantsFollowed.remove(merchant);
			customerRepository.save(customer);
			return "{\"status\": \"OK\", \"msg\": \"OK\"}";
		} else {
			return "{\"status\": \"Error\", \"msg\": \"Error\"}";
		}
	}

}
