package com.wobiancao.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wobiancao.entity.Coupon;
import com.wobiancao.entity.Customer;
import com.wobiancao.entity.Merchant;
import com.wobiancao.protocol.ajax.AjaxFailResponse;
import com.wobiancao.protocol.ajax.AjaxSuccessResponse;
import com.wobiancao.repository.CouponRepository;
import com.wobiancao.repository.CustomerRepository;
import com.wobiancao.repository.MerchantRepository;

@Controller
@RequestMapping(value = "/admin/customer")
public class AdminCustomerController {

	private static final String CURRENT_FUNCTION = "customer";

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private CouponRepository couponRepository;

	@RequestMapping(value = "/list")
	public String list(Pageable pageable, Model model) {
		Page<Customer> page = customerRepository.findAll(pageable);
		model.addAttribute("page", page);
		return String.format("admin/%s/%s_list", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Customer customer = customerRepository.findOne(id);
			model.addAttribute("item", customer);
		}
		return String.format("admin/%s/%s_edit", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestParam(required = false) Long id, @ModelAttribute Customer input) {
		if (id != null) {
			Customer customer = customerRepository.findOne(id);
			if (customer == null) {
				return new AjaxFailResponse("用户不存在").toString();
			}
			customerRepository.save(customer);
		} else {
			customerRepository.save(input);
		}

		return new AjaxSuccessResponse().toString();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(@RequestParam Long id, Model model) {
		Customer customer = customerRepository.findOne(id);
		model.addAttribute("item", customer);
		return String.format("admin/%s/%s_confirm", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam Long id) {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			return new AjaxFailResponse("用户不存在").toString();
		}
		customerRepository.delete(customer);
		return new AjaxSuccessResponse().toString();
	}
	
	@RequestMapping(value = "/merchantsFollowed", method = RequestMethod.GET)
	public String merchantsFollowed(@RequestParam Long id, Model model) {
		if (id != null) {
			Customer customer = customerRepository.findOne(id);
			model.addAttribute("customer", customer);
		}
		return String.format("admin/%s/%s_merchantsFollowed", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}
	
	@RequestMapping(value = "/couponsLiked", method = RequestMethod.GET)
	public String couponsLiked(@RequestParam Long id, Model model) {
		if (id != null) {
			Customer customer = customerRepository.findOne(id);
			model.addAttribute("customer", customer);
		}
		return String.format("admin/%s/%s_couponsLiked", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}
	
	@RequestMapping(value = "/couponsGot", method = RequestMethod.GET)
	public String couponsGot(@RequestParam Long id, Model model) {
		if (id != null) {
			Customer customer = customerRepository.findOne(id);
			model.addAttribute("customer", customer);
		}
		return String.format("admin/%s/%s_couponsGot", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}
	
	@RequestMapping(value = "/followMerchant", method = RequestMethod.GET)
	@ResponseBody
	public String followMerchant(@RequestParam Long id, @RequestParam Long merchantId, Model model) {
		Customer customer = customerRepository.findOne(id);
		Merchant merchant = merchantRepository.findOne(merchantId);
		List<Merchant> merchantsFollowed = customer.getMerchantsFollowed();
		if (!merchantsFollowed.contains(merchant)) {
			merchantsFollowed.add(merchant);
			customerRepository.save(customer);
			return "OK";
		} else {
			return "Error";
		}
	}
	
	@RequestMapping(value = "/unfollowMerchant", method = RequestMethod.GET)
	@ResponseBody
	public String unfollowMerchant(@RequestParam Long id, @RequestParam Long merchantId, Model model) {
		Customer customer = customerRepository.findOne(id);
		Merchant merchant = merchantRepository.findOne(merchantId);
		List<Merchant> merchantsFollowed = customer.getMerchantsFollowed();
		if (merchantsFollowed.contains(merchant)) {
			merchantsFollowed.remove(merchant);
			customerRepository.save(customer);
			return "OK";
		} else {
			return "Error";
		}
	}
	
	@RequestMapping(value = "/likeCoupon", method = RequestMethod.GET)
	@ResponseBody
	public String likeCoupon(@RequestParam Long id, @RequestParam Long couponId, Model model) {
		Customer customer = customerRepository.findOne(id);
		Coupon coupon = couponRepository.findOne(couponId);
		List<Coupon> couponLiked = customer.getCouponsLiked();
		if (!couponLiked.contains(coupon)) {
			couponLiked.add(coupon);
			customerRepository.save(customer);
			return "OK";
		} else {
			return "Error";
		}
	}
	
	@RequestMapping(value = "/unlikeCoupon", method = RequestMethod.GET)
	@ResponseBody
	public String unlikeCoupon(@RequestParam Long id, @RequestParam Long couponId, Model model) {
		Customer customer = customerRepository.findOne(id);
		Coupon coupon = couponRepository.findOne(couponId);
		List<Coupon> couponLiked = customer.getCouponsLiked();
		if (couponLiked.contains(coupon)) {
			couponLiked.remove(coupon);
			customerRepository.save(customer);
			return "OK";
		} else {
			return "Error";
		}
	}
	
	@RequestMapping(value = "/getCoupon", method = RequestMethod.GET)
	@ResponseBody
	public String getCoupon(@RequestParam Long id, @RequestParam Long couponId, Model model) {
		Customer customer = customerRepository.findOne(id);
		Coupon coupon = couponRepository.findOne(couponId);
		List<Coupon> couponGot = customer.getCouponsGot();
		if (!couponGot.contains(coupon)) {
			couponGot.add(coupon);
			customerRepository.save(customer);
		}
		return "OK";
	}
	
}
