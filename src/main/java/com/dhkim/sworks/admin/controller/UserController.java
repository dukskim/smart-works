package com.dhkim.sworks.admin.controller;

import java.util.List;

import com.dhkim.sworks.common.util.PageUtil;
import com.dhkim.sworks.sql.domain.UserInfo;
import com.dhkim.sworks.user.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
	private UserInfoService userInfoService;

	@Value("${config.board.page.count}")
	private int pageCount;
	
	@RequestMapping("/admin/userListView.do")
	public ModelAndView userListView() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setChkPaging(true);
		userInfo.setPageInqCnt(pageCount);
		
		int cnt = userInfoService.getUserInfoListCnt(userInfo);
		PageUtil paging = new PageUtil(userInfo.getPageInqCnt(), cnt, userInfo.getPageNo(), userInfo.getShowPageBlock());
		userInfo.setFirstNo(paging.getStartRow());

		List<UserInfo> userInfoList = userInfoService.getUserInfoList(userInfo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuId", "");
		mv.addObject("paging", paging);
		mv.addObject("userInfoList", userInfoList);
		mv.setViewName("/admin/userListView.tiles");
		return mv;
	}
	
	
}
