package com.dhkim.sworks.test.service;

import java.sql.SQLException;

import com.dhkim.sworks.sql.domain.Test;

/**
 * @author 김덕현
 * 
 */
public interface TestService {
	
	/**
	 * @param test
	 */
	public Test getTest(Test test) throws SQLException;
	
	/**
	 * @param string
	 * @return
	 */
	public void makeTable(String tableName) throws SQLException;
	
	/**
	 * @param test
	 * @return
	 */
	public int addTest(Test test) throws SQLException;
	
}
