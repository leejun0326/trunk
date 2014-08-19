package com.zhc.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhc.jpa.service.SysUserService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired 
	private SysUserService sysUserService;
	
	@RequestMapping("/userlist")
	public String getUserList(Model model){
		model.addAttribute("userList", sysUserService.findAll());
		return "user_list";
	}
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		mav.addObject("username", "LEEJUN");
		return mav;
	}
}
