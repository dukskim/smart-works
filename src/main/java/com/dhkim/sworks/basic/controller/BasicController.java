package com.dhkim.sworks.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.sql.domain.BoardGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController {
	
	@Autowired
	private BoardGroupService boardGroupService;
	
	@RequestMapping("/basic/subject_view.do")
	public ModelAndView subjectView(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String boardId = req.getParameter("boardId");
		BoardGroup bg = new BoardGroup();
		bg.setUseYn("Y");
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(bg);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", boardGroupList);
		mv.addObject("menuId", boardId);
		mv.setViewName("/common/subject_view");
		return mv;
	}
}
