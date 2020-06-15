package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.UserInfo;

@Mapper
public interface UserInfoMapper {
	
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
	public UserInfo selectCntLoginSuccess(UserInfo userInfo) throws SQLException;
	
	/**
	 * @param userId
	 * @return UserInfo
	 * @throws SQLException
	 */
	public UserInfo selectUserInfo(String userId) throws SQLException;

	/**
	 * @param userId
	 * @return int
	 * @throws SQLException
	 */
	public int deleteUser(String userId) throws SQLException;

	/**
	 * @param userInfo
	 * @throws SQLException
	 */
	public void updateUser(UserInfo userInfo) throws SQLException;

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<UserInfo> selectUserInfoList(UserInfo userInfo) throws SQLException;

	/**
	 * @param userInfo
	 * @return
	 */
	public int selectUserInfoListCnt(UserInfo userInfo) throws SQLException;

}
