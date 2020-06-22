package com.dhkim.sworks.test.service;

import java.sql.SQLException;

import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.sql.domain.Test;
import com.dhkim.sworks.sql.mapper.TestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 김덕현
 * 
 */
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class TestServiceImpl extends CommonService implements TestService {
	
	@Autowired
	private TestMapper testMapper;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dhkim.sworks.test.service.TestService#getTest(com.dhkim.sworks.test.domain.Test)
	 */
	@Override
	public Test getTest(Test test) throws SQLException {
		return testMapper.selectTest(test);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dhkim.sworks.test.service.TestService#makeTable(java.lang.String)
	 */
	@Override
	public void makeTable(String tableName) throws SQLException {
		testMapper.createBoard(tableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dhkim.sworks.test.service.TestService#addTest(com.dhkim.sworks.test.domain.Test)
	 */
	@Override
	public int addTest(Test test) throws SQLException {
		return testMapper.insertTest(test);
	}
}
