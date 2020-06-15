/**
 * FileName : BoardAppraisalService.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2015. 6. 25.
 */
package com.dhkim.sworks.board.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.BoardAppraisal;

public interface BoardAppraisalService {
	
	/**
	 * @param boardAppraisal
	 * @return List<BoardAppraisal>
	 * @throws SQLException
	 */
	public List<BoardAppraisal> getBoardAppraisalList(BoardAppraisal boardAppraisal) throws SQLException;

	/**
	 * @param boardAppraisal
	 * @return int
	 * @throws SQLException
	 */
	public int addBoardAppraisal(BoardAppraisal boardAppraisal) throws SQLException;

	/**
	 * @param boardAppraisal
	 * @return int
	 * @throws SQLException
	 */
	public int removeBoardAppraisal(BoardAppraisal boardAppraisal) throws SQLException;
	
}
