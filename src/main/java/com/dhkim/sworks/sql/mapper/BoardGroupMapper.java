package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.BoardGroup;

@Mapper
public interface BoardGroupMapper {
	
	/**
	 * @param boardGroup
	 * @return BoardGroup
	 * @throws SQLException
	 */
	public BoardGroup selectBoardGroup(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardGroup
	 * @return List<BoardGroup>
	 * @throws SQLException
	 */
	public List<BoardGroup> selectBoardGroupList(BoardGroup boardGroup) throws SQLException;

	/**
	 * @param boardGroup
	 * @return int
	 * @throws SQLException
	 */
	public int insertBoardGroup(BoardGroup boardGroup) throws SQLException;
	
	/**
	 * @param boardGroup
	 * @return int
	 * @throws SQLException
	 */
	public int deleteBoardGroup(BoardGroup boardGroup) throws SQLException;

	/**
	 * @param boardGroup
	 * @throws SQLException
	 */
	public void updateBoardGroup(BoardGroup boardGroup) throws SQLException;

	/**
	 * @param boardGroup
	 * @return List<BoardGroup>
	 * @throws SQLException
	 */
	public List<BoardGroup> selectBoardGroupTableNmList(BoardGroup boardGroup) throws SQLException;
	
}
