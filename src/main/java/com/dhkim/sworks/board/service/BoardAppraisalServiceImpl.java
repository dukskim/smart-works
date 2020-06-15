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
import com.dhkim.sworks.sql.domain.BoardAppraisal;
import com.dhkim.sworks.sql.mapper.BoardAppraisalMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BoardAppraisalServiceImpl extends CommonService implements BoardAppraisalService {
	
	@Autowired
	private BoardAppraisalMapper boardAppraisalMapper;

	@Override
	public List<BoardAppraisal> getBoardAppraisalList(
			BoardAppraisal boardAppraisal) throws SQLException {
		return boardAppraisalMapper.selectBoardAppraisalList(boardAppraisal);
	}

	@Override
	public int addBoardAppraisal(BoardAppraisal boardAppraisal)
			throws SQLException {
		return boardAppraisalMapper.insertBoardAppraisal(boardAppraisal);
		
	}

	@Override
	public int removeBoardAppraisal(BoardAppraisal boardAppraisal)
			throws SQLException {
		return boardAppraisalMapper.deleteBoardAppraisal(boardAppraisal);
	}

}
