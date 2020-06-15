package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.Board;

@Mapper
public interface BoardMapper {
	
	/**
	 * @param board
	 * @throws SQLException
	 */
	public void insertBoard(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @throws SQLException
	 */
	public void updateBoard(Board board) throws SQLException;
	
	/**
	 * @param boardNm
	 * @throws SQLException
	 */
	public void create(String boardNm) throws SQLException;
	
	/**
	 * @param boardNm
	 * @throws SQLException
	 */
	public void drop(String boardNm) throws SQLException;
	
	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> selectSearchBoardList(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int selectSearchBoardListCnt(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> selectBoardList(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int selectBoardListCnt(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return Board
	 * @throws SQLException
	 */
	public Board selectBoard(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @throws SQLException
	 */
	public void updateBoardFamilySort(Board board) throws SQLException;
	

	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int deleteBoard(Board board) throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void updateNullBoardUserId(Board board) throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void updateShowCountPlus(Board board) throws SQLException;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> selectBoardTreeUnderStep(Board board) throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void procUnderIdLevel(Board board) throws SQLException;

	/**
	 * @throws SQLException
	 */
	public void deleteTempTreeUnder() throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void updateRecomCountPlus(Board board) throws SQLException;

	/**
	 * @param board
	 * @return int
	 */
	public int selectHotBoardListCnt(Board board) throws SQLException;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> selectHotBoardList(Board board) throws SQLException;

	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int selectRecentBoardListCnt(Board board) throws SQLException;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> selectRecentBoardList(Board board) throws SQLException;
}
