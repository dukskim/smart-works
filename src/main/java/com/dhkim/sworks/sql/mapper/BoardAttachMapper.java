package com.dhkim.sworks.sql.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dhkim.sworks.sql.domain.BoardAttach;

@Mapper
public interface BoardAttachMapper {
	
	public int insertBoardAttach(BoardAttach boardAttach) throws SQLException;

	public List<BoardAttach> selectBoardAttachList(BoardAttach boardAttach) throws SQLException;
	
	public BoardAttach selectBoardAttach(int fileId) throws SQLException;
	
	public int deleteBoardAttach(BoardAttach boardAttach) throws SQLException;
	
}
