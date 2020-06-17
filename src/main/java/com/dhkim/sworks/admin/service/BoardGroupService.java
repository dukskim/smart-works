/**
 * FileName : BoardGroupService.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 3. 6.
 */
package com.dhkim.sworks.admin.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.BoardGroup;

public interface BoardGroupService {
	
	/**
	 * @param boardGroup
	 * @return BoardGroup
	 * @throws SQLException
	 */
	public BoardGroup getBoardGroup(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardId
	 * @return BoardGroup
	 * @throws SQLException
	 */
	public BoardGroup getBoardGroup(String boardId) throws SQLException;
	
	/**
	 * @param boardGroup
	 * @return List<BoardGroup>
	 * @throws SQLException
	 */
	public List<BoardGroup> getBoardGroupList(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardGroup
	 * @return int
	 * @throws SQLException
	 */
	public int addBoardGroup(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardGroup
	 * @throws SQLException
	 */
	public void createBoard(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardId
	 * @param imgPath
	 * @throws Exception
	 */
	public void removeBoardGroup(String boardId) throws Exception;
	
	/**
	 * @param boardGroup
	 * @throws SQLException
	 */
	public void modBoardGroup(BoardGroup boardGroup) throws SQLException;

	/**
	 * @param boardGroup
	 * @return List<BoardGroup>
	 * @throws SQLException
	 */
	public List<BoardGroup> getBoardGroupTableNmList(BoardGroup boardGroup) throws SQLException;
	
}
