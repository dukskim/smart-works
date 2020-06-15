package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.BoardAppraisal;

@Mapper
public interface BoardAppraisalMapper {
	
	/**
	 * @param boardAppraisalNm
	 * @throws SQLException
	 */
	public void create(String boardAppraisalNm) throws SQLException;

	/**
	 * @param boardAppraisalNm
	 * @throws SQLException
	 */
	public void drop(String boardAppraisalNm) throws SQLException;

	/**
	 * @param boardAppraisal
	 * @return List<BoardAppraisal>
	 * @throws SQLException
	 */
	public List<BoardAppraisal> selectBoardAppraisalList(
			BoardAppraisal boardAppraisal) throws SQLException;

	/**
	 * @param boardAppraisal
	 * @return int
	 * @throws SQLException
	 */
	public int insertBoardAppraisal(BoardAppraisal boardAppraisal) throws SQLException;

	/**
	 * @param boardAppraisal
	 * @return int
	 * @throws SQLException
	 */
	public int deleteBoardAppraisal(BoardAppraisal boardAppraisal) throws SQLException;
	
}
