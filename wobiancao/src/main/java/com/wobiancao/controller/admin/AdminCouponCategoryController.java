package com.wobiancao.controller.admin;

import javax.servlet.http.HttpSession;

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

import com.wobiancao.entity.CouponCategory;
import com.wobiancao.protocol.ajax.AjaxFailResponse;
import com.wobiancao.protocol.ajax.AjaxSuccessResponse;
import com.wobiancao.repository.CouponCategoryRepository;

@Controller
@RequestMapping(value = "/admin/couponCategory")
public class AdminCouponCategoryController {

	private static final String CURRENT_FUNCTION = "couponCategory";

	@Autowired
	private CouponCategoryRepository couponCategoryRepository;

	@RequestMapping(value = "/list")
	public String list(Pageable pageable, Model model, HttpSession session) {
		Object admin = session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		}
		
		Page<CouponCategory> page = couponCategoryRepository.findAll(pageable);
		model.addAttribute("page", page);
		return String.format("admin/%s/%s_list", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			CouponCategory couponCategory = couponCategoryRepository.findOne(id);
			model.addAttribute("item", couponCategory);
		}
		return String.format("admin/%s/%s_edit", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestParam(required = false) Long id, @ModelAttribute CouponCategory input) {
		if (id != null) {
			CouponCategory couponCategory = couponCategoryRepository.findOne(id);
			if (couponCategory == null) {
				return new AjaxFailResponse("类别不存在").toString();
			}
			couponCategory.setName(input.getName());
			couponCategory.setDescription(input.getDescription());
			couponCategory.setPicUrl(input.getPicUrl());
			couponCategoryRepository.save(couponCategory);
		} else {
			couponCategoryRepository.save(input);
		}

		return new AjaxSuccessResponse().toString();
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(@RequestParam Long id, Model model) {
		CouponCategory couponCategory = couponCategoryRepository.findOne(id);
		model.addAttribute("item", couponCategory);
		return String.format("admin/%s/%s_confirm", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam Long id) {
		CouponCategory couponCategory = couponCategoryRepository.findOne(id);
		if (couponCategory == null) {
			return new AjaxFailResponse("类别不存在").toString();
		}
		couponCategoryRepository.delete(couponCategory);
		return new AjaxSuccessResponse().toString();
	}

}
