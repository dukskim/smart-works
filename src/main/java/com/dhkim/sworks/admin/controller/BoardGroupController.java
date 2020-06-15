package com.dhkim.sworks.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.admin.service.CodeInfoService;
import com.dhkim.sworks.sql.domain.BoardGroup;
import com.dhkim.sworks.sql.domain.CodeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardGroupController {
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private BoardGroupService boardGroupService;

	@Value("${config.img.upload.url}")
	private String imgUrl;
	
	@RequestMapping("/admin/addBoardGroupView.do")
	public ModelAndView addBoardGroupView() throws Exception {
		
		List<CodeInfo> categoryList = codeInfoService.getCategoryList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryList", categoryList);
		mv.addObject("menu", "");
		mv.setViewName("/admin/addBoardGroupView.tiles");
		return mv;
	}

	@RequestMapping("/admin/boardGroupListView.do")
	public ModelAndView boardGroupList(@ModelAttribute("BoardGroup") BoardGroup boardGroup) throws Exception {
		
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(boardGroup);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardGroupList", boardGroupList);
		mv.addObject("menuId", "");
		mv.setViewName("/admin/boardGroupListView.tiles");
		return mv;
	}
	
	@RequestMapping("/admin/addBoardGroup.do")
	public ModelAndView addBoardGroup(@ModelAttribute("BoardGroup") BoardGroup boardGroup) throws Exception {
		
		boardGroupService.createBoard(boardGroup);
		
		return new ModelAndView("redirect:/admin/boardGroupListView.do");
	}
	
	@RequestMapping("/admin/modifyBoardGroupView.do")
	public ModelAndView modifyBoardGoupView(@ModelAttribute("BoardGroup") BoardGroup boardGroup) throws Exception {
		
		boardGroup = boardGroupService.getBoardGroup(boardGroup);
		List<CodeInfo> categoryList = codeInfoService.getCategoryList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardGroup", boardGroup);
		mv.addObject("categoryList", categoryList);
		mv.addObject("menuId", "");
		mv.setViewName("/admin/modifyBoardGroupView.tiles");
		return mv;
	}
	
	@RequestMapping("/admin/modifyBoardGroup.do")
	public ModelAndView modifyBoardGoup(@ModelAttribute("BoardGroup") BoardGroup boardGroup) throws Exception {
		
		boardGroupService.modBoardGroup(boardGroup);
		
		return new ModelAndView("redirect:/admin/boardGroupListView.do");
	}
	
	@RequestMapping("/admin/removeBoardGroup.do")
	public ModelAndView removeBoardGroup(HttpServletRequest req) throws Exception {
		
		String[] boardIds = req.getParameterValues("boardId");
		
		String imgPath =  req.getSession().getServletContext().getRealPath("/") + imgUrl;
		
		for (int i = 0; i < boardIds.length; i++) {
			boardGroupService.removeBoardGroup(boardIds[i], imgPath);
		}
		return new ModelAndView("redirect:/admin/boardGroupListView.do");
	}
	
	@RequestMapping("/admin/addBoardGroupCheck.do")
	@ResponseBody
	public Map<String, Object> addBoardGroupCheck(@ModelAttribute("BoardGroup") BoardGroup boardGroup, HttpServletResponse res)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BoardGroup> boardList = boardGroupService.getBoardGroupTableNmList(boardGroup);
		if (boardList.size() == 0) {
			map.put("isBoard", 0);
		} else {
			map.put("isBoard", 1);
		}
		
		return map;
	}

}
