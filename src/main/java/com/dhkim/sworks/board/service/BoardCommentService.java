package com.dhkim.sworks.board.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.BoardComment;

public interface BoardCommentService {

	/**
	 * @param boardComment
	 * @return int
	 * @throws SQLException
	 */
	public int getBoardCommentListCnt(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @return List<BoardComment>
	 * @throws SQLException
	 */
	public List<BoardComment> getBoardCommentList(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @return int
	 * @throws SQLException
	 */
	public int addBoardComment(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @throws SQLException
	 */
	public void removeBoardComment(BoardComment boardComment) throws SQLException;

}
