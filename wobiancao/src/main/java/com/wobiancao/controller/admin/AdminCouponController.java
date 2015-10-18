package com.wobiancao.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wobiancao.entity.Coupon;
import com.wobiancao.entity.CouponCategory;
import com.wobiancao.entity.Merchant;
import com.wobiancao.protocol.ajax.AjaxFailResponse;
import com.wobiancao.protocol.ajax.AjaxSuccessResponse;
import com.wobiancao.repository.CouponCategoryRepository;
import com.wobiancao.repository.CouponRepository;
import com.wobiancao.repository.MerchantRepository;

@Controller
@RequestMapping(value = "/admin/coupon")
public class AdminCouponController {

	private static final String CURRENT_FUNCTION = "coupon";

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CouponCategoryRepository couponCategoryRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) Long merchantId, Pageable pageable, Model model) {
		if (merchantId == null) {
			Page<Coupon> page = couponRepository.findAll(pageable);
			model.addAttribute("page", page);
		} else {
			Merchant merchant = merchantRepository.findOne(merchantId);
			model.addAttribute("merchant", merchant);
			Page<Coupon> page = couponRepository.findByMerchantId(merchantId, pageable);
			model.addAttribute("page", page);
		}
		Iterable<CouponCategory> couponCategories = couponCategoryRepository.findAll();
		Map<Long, CouponCategory> couponCategoryMap = new HashMap<Long, CouponCategory>();
		for (CouponCategory c : couponCategories) {
			couponCategoryMap.put(c.getId(), c);
		}
		model.addAttribute("couponCategoryMap", couponCategoryMap);

		return String.format("admin/%s/%s_list", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, @RequestParam(required = false) Long merchantId, Model model) {
		if (id != null) {
			Coupon coupon = couponRepository.findOne(id);
			model.addAttribute("item", coupon);
			Merchant merchant = coupon.getMerchant();
			model.addAttribute("merchants", Arrays.asList(coupon.getMerchant()));
			model.addAttribute("currentMerchantId", merchant.getId());
		} else {
			if (merchantId != null) {
				Merchant merchant = merchantRepository.findOne(merchantId);
				model.addAttribute("merchants", Arrays.asList(merchant));
				model.addAttribute("currentMerchantId", merchantId);
			} else {
				Iterable<Merchant> merchants = merchantRepository.findAll();
				model.addAttribute("merchants", merchants);
			}
		}
		Iterable<CouponCategory> couponCategories = couponCategoryRepository.findAll();
		model.addAttribute("couponCategories", couponCategories);

		return String.format("admin/%s/%s_edit", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestParam(required = false) Long id, @RequestParam(required = false) Long merchantId, @ModelAttribute Coupon input) {
		if (id != null) {
			Coupon coupon = couponRepository.findOne(id);
			if (coupon == null) {
				return new AjaxFailResponse("优惠券不存在").toString();
			}
			coupon.setName(input.getName());
			coupon.setSlogan(input.getSlogan());
			coupon.setDescription(input.getDescription());
			coupon.setCategoryId(input.getCategoryId());
			coupon.setLikeCount(input.getLikeCount());
			coupon.setThirdUrl(input.getThirdUrl());
			coupon.setTimeBegin(input.getTimeBegin());
			coupon.setTimeEnd(input.getTimeEnd());
			coupon.setStatus(input.getStatus());
			couponRepository.save(coupon);
		} else {
			Merchant merchant = merchantRepository.findOne(merchantId);
			input.setMerchant(merchant);
			couponRepository.save(input);
		}

		return new AjaxSuccessResponse().toString();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(@RequestParam Long id, Model model) {
		Coupon coupon = couponRepository.findOne(id);
		model.addAttribute("item", coupon);
		return String.format("admin/%s/%s_confirm", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam Long id) {
		Coupon coupon = couponRepository.findOne(id);
		if (coupon == null) {
			return new AjaxFailResponse("优惠券不存在").toString();
		}
		couponRepository.delete(coupon);
		return new AjaxSuccessResponse().toString();
	}

}
