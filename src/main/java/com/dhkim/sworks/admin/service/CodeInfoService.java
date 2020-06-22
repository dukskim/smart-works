package com.dhkim.sworks.admin.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.CodeInfo;

public interface CodeInfoService {
	
	/**
	 * @return List<CodeInfo>
	 * @throws SQLException
	 */
	public List<CodeInfo> getCategoryList() throws SQLException;

	/**
	 * @param codeId
	 * @return CodeInfo
	 * @throws SQLException
	 */
	public CodeInfo getCategory(String codeId) throws SQLException;

	/**
	 * @param codeInfo
	 * @return int
	 * @throws SQLException
	 */
	public int addCategory(CodeInfo codeInfo) throws SQLException;

	/**
	 * @param codeInfo
	 * @throws SQLException
	 */
	public void modCategory(CodeInfo codeInfo) throws SQLException;

	/**
	 * @param codeId
	 * @throws SQLException
	 */
	public void removeCategory(String codeId) throws SQLException;

}
