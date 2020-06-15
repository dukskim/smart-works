/**
 * FileName : BoardAppraisalServiceImpl.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2015. 6. 25.
 */
package com.dhkim.sworks.board.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.sql.domain.BoardComment;
import com.dhkim.sworks.sql.mapper.BoardCommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BoardCommentServiceImpl extends CommonService implements BoardCommentService {
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	
	@Override
	public int getBoardCommentListCnt(BoardComment boardComment)
			throws SQLException {
		return boardCommentMapper.selectBoardCommentListCnt(boardComment);
	}

	@Override
	public List<BoardComment> getBoardCommentList(BoardComment boardComment)
			throws SQLException {
		return boardCommentMapper.selectBoardCommentList(boardComment);
	}

	@Override
	public int addBoardComment(BoardComment boardComment) throws SQLException {
		return boardCommentMapper.insertBoardComment(boardComment);
	}

	@Override
	public void removeBoardComment(BoardComment boardComment)
			throws SQLException {
		boardCommentMapper.deleteBoardComment(boardComment);
		
	}

}
