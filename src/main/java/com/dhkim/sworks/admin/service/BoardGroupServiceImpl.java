package com.dhkim.sworks.admin.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.common.util.FileControlUtil;
import com.dhkim.sworks.sql.domain.BoardAttach;
import com.dhkim.sworks.sql.domain.BoardGroup;
import com.dhkim.sworks.sql.mapper.BoardCommentMapper;
import com.dhkim.sworks.sql.mapper.BoardAppraisalMapper;
import com.dhkim.sworks.sql.mapper.BoardAttachMapper;
import com.dhkim.sworks.sql.mapper.BoardMapper;
import com.dhkim.sworks.sql.mapper.BoardGroupMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BoardGroupServiceImpl extends CommonService implements BoardGroupService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Autowired
	private BoardAppraisalMapper boardAppraisalMapper;
	
	@Autowired
	private BoardCommentMapper boardCommentMapper;

	@Autowired
	private BoardGroupMapper boardGroupMapper;
	
	@Value("${config.file.upload.path}")
	private String uploadFilePath;

	@Value("${config.file.base.path}")
	private String baseFilePath;
	
	@Value("${config.file.editor.path}")
	private String imgFilePath;
	
	@Override
	public BoardGroup getBoardGroup(BoardGroup boardGroup) throws SQLException {
		return boardGroupMapper.selectBoardGroup(boardGroup);
	}
	
	@Override
	public BoardGroup getBoardGroup(String boardId) throws SQLException {
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setBoardId(boardId);
		return getBoardGroup(boardGroup);
	}
	
	@Override
	public List<BoardGroup> getBoardGroupList(BoardGroup boardGroup) throws SQLException {
		return boardGroupMapper.selectBoardGroupList(boardGroup);
	}
	
	@Override
	public int addBoardGroup(BoardGroup boardGroup) throws SQLException {
		return boardGroupMapper.insertBoardGroup(boardGroup);
	}
	
	@Override
	public void createBoard(BoardGroup boardGroup) throws SQLException {
		int result = boardGroupMapper.insertBoardGroup(boardGroup);
		if (result > 0) {
			boardMapper.create(boardGroup.getBoardNm());  // 게시판 테이블 생성
			boardAppraisalMapper.create(boardGroup.getBoardAppraisalNm()); // 게시판 평가목록 테이블 생성
			boardCommentMapper.create(boardGroup.getBoardCommentNm()); // 게시판 댓글 테이블 생성
		}
	}
	
	@Override
	public void removeBoardGroup(String boardId) throws Exception {
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setBoardId(boardId);
		boardGroup = boardGroupMapper.selectBoardGroup(boardGroup);
		int result = boardGroupMapper.deleteBoardGroup(boardGroup);
		if (result > 0) {
			boardMapper.drop(boardGroup.getBoardNm()); // 게시판 테이블 삭제
			boardAppraisalMapper.drop(boardGroup.getBoardAppraisalNm()); // 게시판 평가목록 테이블 삭제
			boardCommentMapper.drop(boardGroup.getBoardCommentNm());
			
			// 첨부파일 목록도 삭제
			BoardAttach boardAttach = new BoardAttach();
			boardAttach.setBoardId(boardId);
			boardAttachMapper.deleteBoardAttach(boardAttach);

			// 실제 첨부파일 삭제
//			BoardAttach boardAttach = new BoardAttach();
//			boardAttach.setBoardId(boardGroup.getBoardId());
//			List<BoardAttach> boardAttachList = boardDao.getBoardAttachList(boardAttach);
//			for (BoardAttach ba : boardAttachList) {
//				File file = new File(ba.getRealFilePath());
//				if (file.exists()){
//					file.delete();
//				}
//			}
			
			// 조회하여 삭제하는 방식에서 아래방식으로 변경함
			// 실제 첨부파일 삭제 - 폴더 및 하위 파일삭제(boardId 명으로 폴더생성하여 해당 파일을 저장하였으므로 해당이름으로 삭제)
			String filePath = baseFilePath + "/" + uploadFilePath;
			String delFilePath = filePath + "/" + boardGroup.getBoardId();
			Path path = Paths.get(delFilePath);
			delFilePath = path.toString();
			FileControlUtil.deleteDirectory(delFilePath);

			// 이미지 업로드 폴더 삭제
			String imgPath = baseFilePath + "/" + imgFilePath;
			String delImgPath = imgPath + "/" + boardGroup.getBoardId();
			Path ipath = Paths.get(delImgPath);
			delImgPath = ipath.toString();
			FileControlUtil.deleteDirectory(delImgPath);

		}
	}
	
	@Override
	public void modBoardGroup(BoardGroup boardGroup) throws SQLException {
		boardGroupMapper.updateBoardGroup(boardGroup);
	}

	@Override
	public List<BoardGroup> getBoardGroupTableNmList(BoardGroup boardGroup)
			throws SQLException {
		return boardGroupMapper.selectBoardGroupTableNmList(boardGroup);
	}

}
