package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.Test;

@Mapper
public interface TestMapper {
	
	/**
	 * @param test
	 * @return
	 */
	public Test selectTest(Test test) throws SQLException;
	
	/**
	 * @param tableName
	 * @return
	 */
	public void createBoard(String tableName) throws SQLException;
	
	/**
	 * @param test
	 * @return
	 */
	public int insertTest(Test test) throws SQLException;
}
