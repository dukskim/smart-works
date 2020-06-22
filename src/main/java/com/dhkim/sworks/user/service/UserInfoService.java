package com.dhkim.sworks.user.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.sql.domain.UserInfo;


public interface UserInfoService {
	
	/**
	 * @param userInfo
	 * @throws SQLException
	 */
	public void addUser(UserInfo userInfo) throws SQLException;
	
	/**
	 * @param userInfo
	 * @return UserInfo
	 * @throws SQLException
	 */
	public UserInfo getLogin(UserInfo userInfo) throws SQLException;

	/**
	 * @param userId
	 * @return UserInfo
	 * @throws SQLException
	 */
	public UserInfo getUserInfo(String userId) throws SQLException;

	/**
	 * @param userInfo
	 * @throws SQLException
	 */
	public void secede(UserInfo userInfo) throws SQLException;

	/**
	 * @param userInfo
	 * @throws SQLException
	 */
	public void modUser(UserInfo userInfo) throws SQLException;

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<UserInfo> getUserInfoList(UserInfo userInfo) throws SQLException;

	/**
	 * @param userInfo
	 * @return
	 * @throws SQLException
	 */
	public int getUserInfoListCnt(UserInfo userInfo) throws SQLException;

}
