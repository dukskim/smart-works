package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.BoardComment;

@Mapper
public interface BoardCommentMapper {

	/**
	 * @param boardCommentNm
	 * @throws SQLException
	 */
	public void create(String boardCommentNm) throws SQLException;

	/**
	 * @param boardCommentNm
	 * @throws SQLException
	 */
	public void drop(String boardCommentNm) throws SQLException;

	/**
	 * @param boardComment
	 * @return int
	 * @throws SQLException
	 */
	public int selectBoardCommentListCnt(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @return List<BoardComment>
	 * @throws SQLException
	 */
	public List<BoardComment> selectBoardCommentList(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @return int
	 * @throws SQLException
	 */
	public int insertBoardComment(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 * @return int
	 * @throws SQLException
	 */
	public int deleteBoardComment(BoardComment boardComment) throws SQLException;

	/**
	 * @param boardComment
	 */
	public void updateNullBoardCommentUserId(BoardComment boardComment);

}
