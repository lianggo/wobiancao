package com.wobiancao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wobiancao.entity.Customer;
import com.wobiancao.repository.CustomerRepository;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(value = "")
	public String details(HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId()); // 从数据库中取得最新的用户信息
		model.addAttribute("customer", customer);
		
		return "customer/customer_details";
	}
	
	@RequestMapping(value = "/couponsGot")
	public String couponsGot(HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId());
		model.addAttribute("customer", customer);
		return "customer/customer_couponsGot";
	}
	
	@RequestMapping(value = "/merchantFollowed")
	public String merchantFollowed(HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/auth/baseLogin";
		}
		customer = customerRepository.findOne(customer.getId());
		model.addAttribute("customer", customer);
		return "customer/customer_merchantFollowed";
	}

}
