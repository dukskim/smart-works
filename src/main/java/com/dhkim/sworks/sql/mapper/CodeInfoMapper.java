package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.CodeInfo;

@Mapper
public interface CodeInfoMapper {
	
	/**
	 * @return List<CodeInfo>
	 * @throws SQLException
	 */
	public List<CodeInfo> selectCategoryList() throws SQLException;

	/**
	 * @param codeId
	 * @return CodeInfo
	 * @throws SQLException
	 */
	public CodeInfo selectCategory(String codeId) throws SQLException;

	/**
	 * @param codeInfo
	 * @return int
	 * @throws SQLException
	 */
	public int insertCategory(CodeInfo codeInfo) throws SQLException;

	/**
	 * @param codeInfo
	 * @throws SQLException
	 */
	public void updateCategory(CodeInfo codeInfo) throws SQLException;
	
	/**
	 * @param codeId
	 * @throws SQLException
	 */
	public void deleteCategory(String codeId) throws SQLException;

}
