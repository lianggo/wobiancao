package com.wobiancao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@RequestMapping(value = "/home")
	public String login(@RequestParam(required = false) Long categoryId, HttpSession session) {
		return "redirect:/coupon";
	}

}
