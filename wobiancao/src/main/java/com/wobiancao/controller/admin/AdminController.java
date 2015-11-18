package com.wobiancao.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wobiancao.config.AdminProperties;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AdminProperties adminProperties;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/admin/admin_login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String password, HttpSession session, Model model) {
		if (adminProperties.getUsername().equals(username) && adminProperties.getPassword().equals(password)) {
			String admin = adminProperties.getUsername();
			session.setAttribute("admin", admin);
			return "redirect:/admin/coupon/list";
		}
		model.addAttribute("errorMessage", "errorMessage");
		return "/admin/admin_login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/login";
	}

}
