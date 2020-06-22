package com.dhkim.sworks.admin.service;

import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.sql.domain.CodeInfo;
import com.dhkim.sworks.sql.mapper.CodeInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class CodeInfoServiceImpl extends CommonService implements CodeInfoService {

	@Autowired
	private CodeInfoMapper codeInfoMapper;

	@Override
	public List<CodeInfo> getCategoryList()	throws SQLException {
		return codeInfoMapper.selectCategoryList();
	}

	@Override
	public CodeInfo getCategory(String codeId) throws SQLException {
		return codeInfoMapper.selectCategory(codeId);
	}

	@Override
	public int addCategory(CodeInfo codeInfo) throws SQLException {
		return codeInfoMapper.insertCategory(codeInfo);
	}

	@Override
	public void modCategory(CodeInfo codeInfo) throws SQLException {
		codeInfoMapper.updateCategory(codeInfo);
	}

	@Override
	public void removeCategory(String codeId)  throws SQLException {
		codeInfoMapper.deleteCategory(codeId);
	}
	

}
