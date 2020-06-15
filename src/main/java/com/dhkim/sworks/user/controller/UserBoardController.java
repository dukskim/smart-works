package com.dhkim.sworks.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.board.service.BoardCommentService;
import com.dhkim.sworks.board.service.BoardService;
import com.dhkim.sworks.common.util.PageUtil;
import com.dhkim.sworks.sql.domain.Board;
import com.dhkim.sworks.sql.domain.BoardComment;
import com.dhkim.sworks.sql.domain.BoardGroup;
import com.dhkim.sworks.sql.domain.UserInfo;

@Controller
public class UserBoardController {
	
	@Autowired
	private BoardGroupService boardGroupService;

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardCommentService boardCommentService;

	@Value("${config.board.page.count}")
	private int pageCount;

	@RequestMapping("/user/myBoardView.do")
	public ModelAndView myBoardView(HttpServletResponse res) throws Exception {
		
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setUseYn("Y");
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(boardGroup);

		ModelAndView mv = new ModelAndView();
		mv.addObject("boardGroupList", boardGroupList);
		mv.addObject("menuId", "");
		mv.setViewName("/user/myBoardView.tiles");
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/user/myBoard.do")
	public ModelAndView myBoard(@ModelAttribute("Board") Board board, HttpSession session) throws Exception {
		UserInfo suser = (UserInfo)session.getAttribute("userInfo");
		
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setUseYn("Y");
		boardGroup.setBoardId(board.getBoardId());
		boardGroup = boardGroupService.getBoardGroup(boardGroup);
		PageUtil paging;
		List boardList;
		if(board.getBoardDiv().equals(Board.DIV_COMMENT)){
			BoardComment boardComment = new BoardComment();
			boardComment.setBoardCommentNm(boardGroup.getBoardCommentNm());
			boardComment.setUserId(suser.getUserId());
			boardComment.setBoardId(board.getBoardId());
			boardComment.setPageInqCnt(pageCount);
			int cnt = boardCommentService.getBoardCommentListCnt(boardComment);
			paging = new PageUtil(boardComment.getPageInqCnt(), cnt, boardComment.getPageNo(), boardComment.getShowPageBlock());
			boardComment.setFirstNo(paging.getStartRow());
			
			boardList = boardCommentService.getBoardCommentList(boardComment);
		
		} else {
			board.setUserId(suser.getUserId());
			board.setBoardNm(boardGroup.getBoardNm());
			board.setBoardCommentNm(boardGroup.getBoardCommentNm());
			board.setDelYn("N");
			board.setPageInqCnt(pageCount);
			int cnt = boardService.getBoardListCnt(board);
			paging = new PageUtil(board.getPageInqCnt(), cnt, board.getPageNo(), board.getShowPageBlock());
			board.setFirstNo(paging.getStartRow());
			
			boardList = boardService.getBoardList(board);
		}
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("paging", paging);
		mv.addObject("boardList", boardList);
		mv.addObject("boardDiv", board.getBoardDiv());
		mv.addObject("boardId", boardGroup.getBoardId());
		mv.addObject("menuId", "");
		mv.setViewName("/user/view/myBoard");
		return mv;
	}
	
	
	
}
