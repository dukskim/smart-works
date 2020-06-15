package com.dhkim.sworks.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dhkim.sworks.sql.domain.UserInfo;
import com.dhkim.sworks.user.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/")
	public ModelAndView index() {
		
		return new ModelAndView("index");
	}

	@RequestMapping("/login/loginView.do")
	public ModelAndView loginView() {
		
		return new ModelAndView("login");
	}
	
	@RequestMapping("/login/joinUserView.do")
	public ModelAndView joinUser() {
		
		return new ModelAndView("joinUserView");
	}
	
	@RequestMapping("/login/login.do")
	public ModelAndView login(@ModelAttribute("UserInfo") UserInfo userInfo, HttpSession session) throws Exception {
		
		userInfo = userInfoService.getLogin(userInfo);
		if (userInfo != null) {
			// Login Success : Session Put
			session.setAttribute("userInfo", userInfo);
			return new ModelAndView("redirect:/home/main.do");
		} else {
			return new ModelAndView("loginFaild", new HashMap<String, Object>());
		}
	}
	
	@ResponseBody
	@RequestMapping("/login/addUser.do")
	public Map<String, Object> addUser(@ModelAttribute("UserInfo") UserInfo userInfo, HttpServletResponse res) throws Exception {
		
		userInfo.setPoint(0);
		userInfo.setUserLevel(0);
		userInfo.setRegStatus("1");
		
		userInfoService.addUser(userInfo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "1");
		
		return map;
	}
	
	@RequestMapping("/login/logout.do")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		
		return new ModelAndView("redirect:/login/loginView.do");
	}
	
}
