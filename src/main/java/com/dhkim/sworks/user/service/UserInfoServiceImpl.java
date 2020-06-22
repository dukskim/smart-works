package com.dhkim.sworks.user.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.board.service.BoardAppraisalService;
import com.dhkim.sworks.board.service.BoardService;
import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.sql.domain.BoardAppraisal;
import com.dhkim.sworks.sql.domain.BoardGroup;
import com.dhkim.sworks.sql.domain.UserInfo;
import com.dhkim.sworks.sql.mapper.UserInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class UserInfoServiceImpl extends CommonService implements UserInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardGroupService boardGroupService;
	
	@Autowired
	private BoardAppraisalService boardAppraisalService;

	@Override
	public void addUser(UserInfo userInfo) throws SQLException {
		userInfoMapper.addUser(userInfo);
	}

	@Override
	public UserInfo getLogin(UserInfo userInfo) throws SQLException {
		return userInfoMapper.selectCntLoginSuccess(userInfo);
	}
	
	@Override
	public UserInfo getUserInfo(String userId) throws SQLException {
		return userInfoMapper.selectUserInfo(userId);
	}

	@Override
	public void secede(UserInfo userInfo) throws SQLException {
		
		// 사용자 게시물 userId null 처리(게시글, 답변, 코멘트)
		boardService.updateNullBoardUserId(userInfo.getUserId());

		// 사용자 관련 평가 테이블의 항목 삭제처리
		List<BoardGroup> boardGroupList =  boardGroupService.getBoardGroupList(new BoardGroup());
		for (BoardGroup boardGroup : boardGroupList) {
			BoardAppraisal boardAppraisal = new BoardAppraisal();
			boardAppraisal.setBoardAppraisalNm(boardGroup.getBoardAppraisalNm());
			boardAppraisal.setUserId(userInfo.getUserId());
			boardAppraisalService.removeBoardAppraisal(boardAppraisal);
		}
		
		// 사용자 삭제
		userInfoMapper.deleteUser(userInfo.getUserId());
	}

	@Override
	public void modUser(UserInfo userInfo) throws SQLException {
		userInfoMapper.updateUser(userInfo);
	}

	@Override
	public List<UserInfo> getUserInfoList(UserInfo userInfo) throws SQLException {
		return userInfoMapper.selectUserInfoList(userInfo);
	}

	@Override
	public int getUserInfoListCnt(UserInfo userInfo) throws SQLException {
		return userInfoMapper.selectUserInfoListCnt(userInfo);
	}
}
