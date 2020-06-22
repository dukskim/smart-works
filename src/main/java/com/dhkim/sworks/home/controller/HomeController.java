package com.dhkim.sworks.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.board.service.BoardService;
import com.dhkim.sworks.common.util.PageUtil;
import com.dhkim.sworks.sql.domain.Board;
import com.dhkim.sworks.sql.domain.BoardGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardGroupService boardGroupService;

	@Value("${config.main.board.list.size}")
	private int listSize;
	
	@Value("${config.main.board.hot.month.during}")
	private int duringMonth;
	
	@RequestMapping("/home/main.do")
	public ModelAndView boardGroupList(@ModelAttribute("BoardGroup") BoardGroup boardGroup) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuId", "");
		mv.setViewName("/home/main.tiles");
		return mv;
	}
	
	@RequestMapping("/home/home_board_summary.do")
	public ModelAndView boardSummaryList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String mainType = req.getParameter("mainType");
		if (mainType == null || mainType.isEmpty()){
			mainType = BoardGroup.SHOW_MAIN_RECOMMAND_BOARD_ALL;
		}
		
		BoardGroup bg = new BoardGroup();
		bg.setShowMainYn("Y");
		bg.setUseYn("Y");
		bg.setSortField("SORT_SEQ");
		bg.setSortValue("ASC");
		
		List<BoardGroup> bgList = boardGroupService.getBoardGroupList(bg);
		List<Map<String, Object>> viewBoardGroupList = new ArrayList<Map<String, Object>>();
		
		for (BoardGroup boardGroup : bgList) {
			Board board = new Board();
			board.setBoardNm(boardGroup.getBoardNm());
			board.setPageInqCnt(listSize);
			board.setBoardCommentNm(boardGroup.getBoardCommentNm());
			List<Board> boardList = new ArrayList<Board>();
			int cnt = 0;
			PageUtil paging;
			switch (mainType) {
			case BoardGroup.SHOW_MAIN_RECENT_BOARD:
				cnt = boardService.getRecentBoardListCnt(board);
				paging = new PageUtil(board.getPageInqCnt(), cnt, board.getPageNo(), board.getShowPageBlock());
				board.setFirstNo(paging.getStartRow());
				boardList = boardService.getRecentBoardList(board);
				break;
			case BoardGroup.SHOW_MAIN_RECOMMAND_BOARD_ALL:
				cnt = boardService.getHotBoardListCnt(board);
				paging = new PageUtil(board.getPageInqCnt(), cnt, board.getPageNo(), board.getShowPageBlock());
				board.setFirstNo(paging.getStartRow());
				boardList = boardService.getHotBoardList(board);
				break;
			case BoardGroup.SHOW_MAIN_RECOMMAND_BOARD_MONTH:
				board.setDuringMonth(duringMonth);
				cnt = boardService.getHotBoardListCnt(board);
				paging = new PageUtil(board.getPageInqCnt(), cnt, board.getPageNo(), board.getShowPageBlock());
				board.setFirstNo(paging.getStartRow());
				boardList = boardService.getHotBoardList(board);
				break;
			default:
				break;
			}

			Map<String, Object> rstMap = new HashMap<String, Object>();
			rstMap.put("boardGroup", boardGroup);
			rstMap.put("boardList", boardList);
			viewBoardGroupList.add(rstMap);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("viewBoardGroupList", viewBoardGroupList);
		mv.setViewName("home/view/home_board_summary");
		return mv;
	}
	
	
}
