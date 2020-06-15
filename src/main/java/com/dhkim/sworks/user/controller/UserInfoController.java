/**
 * FileName : UserInfoController.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 2. 16.
 */
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
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/user/modUserView.do")
	public ModelAndView modUserView(@ModelAttribute("UserInfo") UserInfo userInfo, HttpServletResponse res) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuId", "");
		mv.setViewName("/user/modUserView.tiles");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/user/modUser.do")
	public Map<String, Object> modUser(@ModelAttribute("UserInfo") UserInfo userInfo, HttpServletResponse res, HttpSession session) throws Exception {
		UserInfo suser = (UserInfo)session.getAttribute("userInfo");
		userInfo.setUserId(suser.getUserId());
		
//		log.debug("modUserId : "+userInfo.getUserId());
		UserInfo user = userInfoService.getUserInfo(userInfo.getUserId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (user != null){
			userInfo.setPoint(user.getPoint());
			userInfo.setRegDt(userInfo.getRegDt());
			userInfo.setRegStatus(user.getRegStatus());
			userInfo.setUserLevel(user.getUserLevel());
			userInfoService.modUser(userInfo);
			
			map.put("result", "1");
			session.setAttribute("userInfo", userInfo);
		} else {
			map.put("result", "0");
		}
		
		return map;
	}
	
	@RequestMapping("/user/secede.do")
	public ModelAndView secede(HttpSession session) throws Exception {
		
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		// 탈퇴처리
		userInfoService.secede(userInfo);
		
		return new ModelAndView("redirect:/login/logout.do");
	}
	
	@RequestMapping("/user/userInfoView.do")
	public ModelAndView userInfoView(@ModelAttribute("UserInfo") UserInfo userInfo, HttpServletResponse res) throws Exception {
		UserInfo user = userInfoService.getUserInfo(userInfo.getUserId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("/user/popup/userInfoView");
		return mv;
	}

	
}
