/**
 * FileName : BoardService.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 3. 6.
 */
package com.dhkim.sworks.board.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.Board;
import com.dhkim.sworks.sql.domain.BoardAttach;

public interface BoardService {
	
	/**
	 * @param board
	 * @throws Exception
	 */
	public void addBoard(Board board) throws Exception;

	/**
	 * @param board
	 * @throws Exception
	 */
	public void modBoard(Board board) throws Exception;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> getSearchBoardList(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int getSearchBoardListCnt(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> getBoardList(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int getBoardListCnt(Board board) throws SQLException;
	
	/**
	 * @param board
	 * @return Board
	 * @throws SQLException
	 */
	public Board getBoard(Board board) throws SQLException;
	
	/**
	 * @param boardId
	 * @param boardSeq
	 * @return Board
	 * @throws SQLException
	 */
	public Board getBoard(String boardId, int boardSeq) throws SQLException;

	/**
	 * @param boardAttach
	 * @return List<BoardAttach>
	 * @throws SQLException
	 */
	public List<BoardAttach> getBoardAttachList(BoardAttach boardAttach) throws SQLException;

	/**
	 * @param fileId
	 * @return BoardAttach
	 * @throws SQLException
	 */
	public BoardAttach getBoardAttach(int fileId) throws SQLException;
	
	/**
	 * @param board
	 * @throws Exception
	 */
	public void removeBoard(Board board) throws Exception;

	/**
	 * @param fileId
	 * @throws Exception
	 */
	public void removeBoardAttachFile(int fileId) throws Exception;

	/**
	 * @param userId
	 * @throws SQLException
	 */
	public void updateNullBoardUserId(String userId) throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void modShowCountPlus(Board board) throws SQLException;

	/**
	 * @param board
	 * @throws SQLException
	 */
	public void modRecomCountPlus(Board board) throws SQLException;

	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int getHotBoardListCnt(Board board) throws SQLException;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> getHotBoardList(Board board) throws SQLException;

	/**
	 * @param board
	 * @return int
	 * @throws SQLException
	 */
	public int getRecentBoardListCnt(Board board) throws SQLException;

	/**
	 * @param board
	 * @return List<Board>
	 * @throws SQLException
	 */
	public List<Board> getRecentBoardList(Board board) throws SQLException;

}
