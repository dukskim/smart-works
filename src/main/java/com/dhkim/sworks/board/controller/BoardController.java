/**
 * FileName : BoardController.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 3. 6.
 */
package com.dhkim.sworks.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.board.service.BoardAppraisalService;
import com.dhkim.sworks.board.service.BoardCommentService;
import com.dhkim.sworks.board.service.BoardService;
import com.dhkim.sworks.common.util.PageUtil;
import com.dhkim.sworks.common.view.DownloadFile;
import com.dhkim.sworks.sql.domain.Board;
import com.dhkim.sworks.sql.domain.BoardAppraisal;
import com.dhkim.sworks.sql.domain.BoardAttach;
import com.dhkim.sworks.sql.domain.BoardComment;
import com.dhkim.sworks.sql.domain.BoardGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardGroupService boardGroupService;
	
	@Autowired
	private BoardAppraisalService boardAppraisalService;
	
	@Autowired
	private BoardCommentService boardCommentService;
	
	@Value("${config.file.upload.path}")
	private String baseFilePath;
	
	@Value("${config.board.page.count}")
	private int pageCount;
	
	@RequestMapping("/board/addBoard.do")
	public ModelAndView addBoard(@ModelAttribute("Board") Board board) throws Exception {
		if (board.getBoardDiv() == null) {
			board.setBoardDiv(Board.DIV_NORMAL);
		}
		
		boardService.addBoard(board);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", board.getBoardId());
		mv.setViewName("redirect:/board/boardList.do");
		return mv;
	}
	@RequestMapping("/board/modBoard.do")
	public ModelAndView modBoard(@ModelAttribute("Board") Board board) throws Exception {

		boardService.modBoard(board);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", board.getBoardId());
		mv.setViewName("redirect:/board/boardList.do");
		return mv;
	}
	
	@RequestMapping("/board/addBoardView.do")
	public ModelAndView addBoardView(@ModelAttribute("Board") Board board) throws Exception {
		
		// 파라미터 체크
		if (board.getBoardDiv() == null || "".equals(board.getBoardDiv())) {
			board.setBoardDiv(Board.DIV_NORMAL);
		}
		
		ModelAndView mv = new ModelAndView();
		
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setUseYn("Y");
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(boardGroup);
		
		boardGroup.setBoardId(board.getBoardId());
		boardGroup = boardGroupService.getBoardGroup(boardGroup);
		
		// 답변일 경우 원본글을 가져온다.
		if (Board.DIV_REPLAY.equals(board.getBoardDiv())) {
			Board bd = new Board();
			bd.setBoardNm(boardGroup.getBoardNm());
			bd.setBoardSeq(board.getBoardSeq());
			bd = boardService.getBoard(bd);
			mv.addObject("orgBoard", bd);
		}
		
		mv.addObject("boardGroup", boardGroup);
		mv.addObject("boardGroupList", boardGroupList);
		mv.addObject("board", board);
		mv.addObject("menuId", board.getBoardId());
		mv.setViewName("/board/addBoardView.tiles");
		return mv;
	}
	
	@RequestMapping("/board/modBoardView.do")
	public ModelAndView modBoardView(@ModelAttribute("Board") Board board) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setUseYn("Y");
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(boardGroup);
		
		boardGroup.setBoardId(board.getBoardId());
		boardGroup = boardGroupService.getBoardGroup(boardGroup);

		board.setBoardNm(boardGroup.getBoardNm());
		board = boardService.getBoard(board);
		
		mv.addObject("boardGroup", boardGroup);
		mv.addObject("boardGroupList", boardGroupList);
		mv.addObject("board", board);
		mv.addObject("menuId", board.getBoardId());
		mv.setViewName("/board/modBoardView.tiles");
		return mv;
	}
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(@ModelAttribute("Board") Board board) throws Exception {
		
		BoardGroup bg = new BoardGroup();
		bg.setUseYn("Y");
		List<BoardGroup> boardGroupList = boardGroupService.getBoardGroupList(bg);
		
		if (board.getSearchGroup() != null && !"".equals(board.getSearchGroup())) {
			board.setBoardId(board.getSearchGroup());
		}
		
		bg.setBoardId(board.getBoardId());
		bg = boardGroupService.getBoardGroup(bg);
		
		board.setBoardNm(bg.getBoardNm());
		board.setDelYn("N");
		board.setPageInqCnt(pageCount);
		board.setBoardCommentNm(bg.getBoardCommentNm());
		
		int cnt = boardService.getSearchBoardListCnt(board);
		PageUtil paging = new PageUtil(board.getPageInqCnt(), cnt, board.getPageNo(), board.getShowPageBlock());
		board.setFirstNo(paging.getStartRow());
		List<Board> boardList = boardService.getSearchBoardList(board);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("paging", paging);
		mv.addObject("boardGroup", bg);
		mv.addObject("boardGroupList", boardGroupList);
		mv.addObject("boardList", boardList);
		mv.addObject("board", board);
		mv.addObject("menuId", bg.getBoardId());
		mv.setViewName("/board/boardList.tiles");
		return mv;
	}
	
	@RequestMapping("/board/viewBoard.do")
	public ModelAndView viewBoard(@ModelAttribute("Board") Board board) throws Exception {
		
		BoardGroup bg = new BoardGroup();
		bg.setBoardId(board.getBoardId());
		bg = boardGroupService.getBoardGroup(bg);
		board.setBoardNm(bg.getBoardNm());
		boardService.modShowCountPlus(board);
		Board bd = boardService.getBoard(board);
		bd.setBoardId(board.getBoardId());
		
		BoardAttach ba = new BoardAttach();
		ba.setBoardId(bd.getBoardId());
		ba.setBoardSeq(bd.getBoardSeq());
		List<BoardAttach> boardAttachList = boardService.getBoardAttachList(ba);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardGroup", bg);
		mv.addObject("board", bd);
		mv.addObject("boardAttachList", boardAttachList);
		mv.addObject("menuId", bg.getBoardId());
		mv.setViewName("/board/board.tiles");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/board/addComment.do")
	public Map<String, Object> addComment(@ModelAttribute("BoardComment") BoardComment boardComment, HttpServletResponse res) throws Exception {
		
		BoardGroup bg = boardGroupService.getBoardGroup(boardComment.getBoardId());
		boardComment.setBoardCommentNm(bg.getBoardCommentNm());
		boardCommentService.addBoardComment(boardComment);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "1");
		
		return map;
		
	}

	@ResponseBody
	@RequestMapping("/board/deleteComment.do")
	public Map<String, Object> deleteComment(@ModelAttribute("BoardComment") BoardComment boardComment, HttpServletResponse res) throws Exception {
		
		BoardGroup boardGroup = boardGroupService.getBoardGroup(boardComment.getBoardId());
		boardComment.setBoardCommentNm(boardGroup.getBoardCommentNm());
		boardCommentService.removeBoardComment(boardComment);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "1");
		
		return map;
		
	}
	
	@RequestMapping("/board/board_commentList.do")
	public ModelAndView commentList(@ModelAttribute("BoardComment") BoardComment boardComment, HttpServletRequest req) throws Exception {
		BoardGroup bg = boardGroupService.getBoardGroup(boardComment.getBoardId());
		
		boardComment.setBoardCommentNm(bg.getBoardCommentNm());
		boardComment.setChkPaging(true);
		int cnt = boardCommentService.getBoardCommentListCnt(boardComment);
		PageUtil paging = new PageUtil(boardComment.getPageInqCnt(), cnt, boardComment.getPageNo(), boardComment.getShowPageBlock());
		boardComment.setFirstNo(paging.getStartRow());
		List<BoardComment> boardCommentList = boardCommentService.getBoardCommentList(boardComment);
		
		BoardComment bc = new BoardComment();
		bc.setBoardSeq(boardComment.getBoardSeq());
		ModelAndView mv = new ModelAndView();
		mv.addObject("paging", paging);
		mv.addObject("boardComment", bc);
		mv.addObject("boardGroup", bg);
		mv.addObject("boardCommentList", boardCommentList);
		mv.setViewName("/board/view/board_commentList");
		return mv;
	}
	
	@RequestMapping("/board/board_attachList.do")
	public ModelAndView attachList(@ModelAttribute("Board") Board board) throws Exception {

		BoardAttach ba = new BoardAttach();
		ba.setBoardId(board.getBoardId());
		ba.setBoardSeq(board.getBoardSeq());
		List<BoardAttach> boardAttachList = boardService.getBoardAttachList(ba);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardAttachList", boardAttachList);
		mv.setViewName("/board/view/board_attachList");
		return mv;
	}
	
	@RequestMapping("/board/fileDownload.do")
	public ModelAndView fileDownload(@ModelAttribute("BoardAttach") BoardAttach boardAttach) throws Exception {

		BoardAttach ba = boardService.getBoardAttach(boardAttach.getFileId());
		DownloadFile downloadFile = new DownloadFile(); 
		downloadFile.setFile(new File(ba.getRealFilePath()));
		downloadFile.setOrgFileName(ba.getFileNm());

		return new ModelAndView("downloadView", "downloadFile", downloadFile);

	}

	@ResponseBody
	@RequestMapping("/board/fileDelete.do")
	public Map<String, Object> fileDelete(@ModelAttribute("BoardAttach") BoardAttach boardAttach, HttpServletResponse res)
				throws Exception {
		
		boardService.removeBoardAttachFile(boardAttach.getFileId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "1");
		
		return map;
		
	}
	
	@RequestMapping("/board/removeBoard.do")
	public ModelAndView removeBoard(@ModelAttribute("Board") Board board) throws Exception {
		
		boardService.removeBoard(board);

		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", board.getBoardId());
		mv.setViewName("redirect:/board/boardList.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/board/modRecomCountPlus.do")
	public Map<String, Object> modRecomCountPlus(@ModelAttribute("Board") Board board, HttpServletResponse res) throws Exception {
		
		BoardGroup boardGroup = boardGroupService.getBoardGroup(board.getBoardId());
		
		BoardAppraisal boardAppraisal = new BoardAppraisal();
		boardAppraisal.setUserId(board.getUserId());
		boardAppraisal.setBoardAppraisalNm(boardGroup.getBoardAppraisalNm());
		boardAppraisal.setBoardSeq(board.getBoardSeq());
		
		List<BoardAppraisal> baList = boardAppraisalService.getBoardAppraisalList(boardAppraisal);
		
		String result = "0";
		if (baList.size() == 0){
			boardAppraisal.setAppraisalType(BoardAppraisal.RECOMMEND);
			boardAppraisalService.addBoardAppraisal(boardAppraisal);

			board.setBoardNm(boardGroup.getBoardNm());
			boardService.modRecomCountPlus(board);
			
			result = "1";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		
		return map;
		
	}
}
