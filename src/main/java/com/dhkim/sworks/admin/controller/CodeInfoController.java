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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CodeInfoController {
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private BoardGroupService boardGroupService;
	
	@RequestMapping("/admin/addCategoryView.do")
	public ModelAndView addCategoryView() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menu", "");
		mv.setViewName("/admin/addCategoryView.tiles");
		return mv;
	}
	
	@RequestMapping("/admin/categoryListView.do")
	public ModelAndView categoryListView() throws Exception {
		
		List<CodeInfo> categoryList = codeInfoService.getCategoryList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryList", categoryList);
		mv.addObject("menu", "");
		mv.setViewName("/admin/categoryListView.tiles");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/admin/addCategoryCheck.do")
	public Map<String, Object> addCategoryCheck(@ModelAttribute("CodeInfo") CodeInfo codeInfo, HttpServletResponse res)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(); 
		CodeInfo category = codeInfoService.getCategory(codeInfo.getCodeId());
		if (category == null) {
			map.put("isCategory", 0);
			
		} else {
			map.put("isCategory", 1);
		}
		return map;
	}
	
	@RequestMapping("/admin/addCategory.do")
	public ModelAndView addCategory(@ModelAttribute("CodeInfo") CodeInfo codeInfo) throws Exception {
		
		codeInfoService.addCategory(codeInfo);
		
		return new ModelAndView("redirect:/admin/categoryListView.do");
	}

	@RequestMapping("/admin/modifyCategoryView.do")
	public ModelAndView modifyCategoryView(@ModelAttribute("CodeInfo") CodeInfo codeInfo) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		CodeInfo ci = codeInfoService.getCategory(codeInfo.getCodeId());
		mv.addObject("codeInfo", ci);
		mv.addObject("menuId", "");
		mv.setViewName("/admin/modifyCategoryView.tiles");
		return mv;
	}
	
	@RequestMapping("/admin/modifyCategory.do")
	public ModelAndView modifyCategory(@ModelAttribute("CodeInfo") CodeInfo codeInfo) throws Exception {
		
		codeInfoService.modCategory(codeInfo);

		return new ModelAndView("redirect:/admin/categoryListView.do");
	}

	@ResponseBody
	@RequestMapping("/admin/removeCategoryCheck.do")
	public Map<String, Object> removeCategoryCheck(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] codeIds = req.getParameterValues("codeId");
		int isCategory = 0;
		for (int i = 0; i < codeIds.length; i++) {
			BoardGroup boardGroup = new BoardGroup();
			boardGroup.setCategory(codeIds[i]);
			List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(boardGroup);
			if (boardGroupList.size() > 0) {
				isCategory = 1;
				map.put("codeId", codeIds[i]);
				break;
			}
		}
		map.put("isCategory", isCategory);
		return map;
		
	}
	
	@RequestMapping("/admin/removeCategory.do")
	public ModelAndView removeCategory(HttpServletRequest req) throws Exception {
		
		String[] codeIds = req.getParameterValues("codeId");
		
		for (int i = 0; i < codeIds.length; i++) {
			codeInfoService.removeCategory(codeIds[i]);
		}
		return new ModelAndView("redirect:/admin/categoryListView.do");
	}

}
