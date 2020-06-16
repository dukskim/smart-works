/**
 * FileName : BoardServiceImpl.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 3. 6.
 */
package com.dhkim.sworks.board.service;

import java.nio.file.Path;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import com.dhkim.sworks.admin.service.BoardGroupService;
import com.dhkim.sworks.common.service.CommonService;
import com.dhkim.sworks.common.util.FileControlUtil;
import com.dhkim.sworks.sql.domain.Board;
import com.dhkim.sworks.sql.domain.BoardAppraisal;
import com.dhkim.sworks.sql.domain.BoardAttach;
import com.dhkim.sworks.sql.domain.BoardComment;
import com.dhkim.sworks.sql.domain.BoardGroup;
import com.dhkim.sworks.sql.mapper.BoardAttachMapper;
import com.dhkim.sworks.sql.mapper.BoardCommentMapper;
import com.dhkim.sworks.sql.mapper.BoardMapper;

import ch.qos.logback.classic.Logger;

import com.dhkim.sworks.sql.mapper.BoardGroupMapper;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BoardServiceImpl extends CommonService implements BoardService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardGroupService boardGroupService;
	
	@Autowired
	private BoardAppraisalService boardAppraisalService;
	
	@Autowired
	private BoardCommentService boardCommentService;
	
	@Autowired
	private BoardCommentMapper boardCommentMapper;
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Autowired
	private BoardGroupMapper boardGroupMapper;
	
	@Value("${config.file.base.path}")
	private String baseFilePath;

	@Value("${config.file.upload.path}")
	private String uploadFilePath;

	
	@Override
	public void addBoard(Board board) throws Exception {

		BoardGroup bg = boardGroupService.getBoardGroup(board.getBoardId());
		board.setBoardNm(bg.getBoardNm());
		if (Board.DIV_REPLAY.equals(board.getBoardDiv())){
			Board orgBoard = new Board();
			orgBoard.setBoardNm(bg.getBoardNm());
			orgBoard.setBoardSeq(board.getRefBoardSeq());
			orgBoard = getBoard(orgBoard);
			board.setBoardDiv(Board.DIV_REPLAY);
			board.setBoardFamily(orgBoard.getBoardFamily());
			board.setBoardFamilySort(orgBoard.getBoardFamilySort() + 1);
			board.setBoardStep(orgBoard.getBoardStep() + 1);
			boardMapper.updateBoardFamilySort(board);
		} else if (Board.DIV_COMMENT.equals(board.getBoardDiv())) {
			board.setRefBoardSeq(board.getBoardSeq());
			board.setBoardSeq(0);
			board.setBoardDiv(Board.DIV_COMMENT);
		} else { //Board.DIV_NORMAL.equals(board.getBoardDiv())
			board.setRefBoardSeq(0);
			board.setBoardFamilySort(1);
		}
		
		board.setDelYn("N");
		board.setPoint(0);
		board.setRecomCnt(0);
		board.setShowCnt(0);		
		
		boardMapper.insertBoard(board);
		
		fileUpload(board);
	}

	@Override
	public void modBoard(Board board) throws Exception {
		
		BoardGroup bg = boardGroupService.getBoardGroup(board.getBoardId());
		board.setBoardNm(bg.getBoardNm());
		String userId = board.getUserId();
		logger.debug("aaaaa userId:"+userId);
		board.setUserId(null); // 관리자가 변경하는 경우가 있어서 파라미터로 userId 넘어갈 경우 게시물을 못찾는경우생김
		Board bd = getBoard(board);
		bd.setBoardContent(board.getBoardContent());
		bd.setTitle(board.getTitle());
		bd.setBoardNm(bg.getBoardNm());
		bd.setUserId(userId);
		boardMapper.updateBoard(bd);
		
		board.setBoardSeq(bd.getBoardSeq());
		fileUpload(board);
		
	}

	private void fileUpload(Board board) throws Exception {
		// 파일 업로드
		if (board.getAttachFile() != null) {
			// 디렉토리 체크
			log.debug("File Upload Directory Check !!!");
			
			String saveFilePath = baseFilePath + "/" + uploadFilePath + "/" + board.getBoardId() + "/" + board.getBoardSeq();
			Path path = Paths.get(saveFilePath);
			saveFilePath = path.toString();
			log.debug("saveFilePath:"+saveFilePath);
			File realUploadDir = new File(saveFilePath); // 업로드 폴더 위치
			
			FileControlUtil.createDirectory(realUploadDir);
			
			// 파일 업로드
			for (MultipartFile uploadFile : board.getAttachFile()) {
				if (!uploadFile.isEmpty()) {
					// 파일명
					File savefile = null;
					do {
						String fileName = System.currentTimeMillis() + uploadFile.getOriginalFilename();
						String svf = saveFilePath + "/" + fileName;
						Path svfpath = Paths.get(svf);
						svf = svfpath.toString();
						savefile = new File(svf);
					} while (savefile.exists());
					
					log.debug("Upload Orginal File Name : " + uploadFile.getName());
					log.debug("Upload Save File Name : " + savefile.getPath()); 
					log.debug("Upload Orginal File Name : " + uploadFile.getOriginalFilename());
					// 업로드 목록 DB 저장
					log.debug("File Upload Insert !!!");
					BoardAttach boardAttach = new BoardAttach();
					boardAttach.setBoardSeq(board.getBoardSeq());
					boardAttach.setFileNm(uploadFile.getOriginalFilename());
					boardAttach.setRealFilePath(savefile.getPath());
					boardAttach.setBoardId(board.getBoardId());
					
					int addCnt = boardAttachMapper.insertBoardAttach(boardAttach);
					log.debug("addCnt" + addCnt);
					
					uploadFile.transferTo(savefile);
				}
			}
			
		}
	}
	
	@Override
	public List<Board> getSearchBoardList(Board board) throws SQLException {
		return boardMapper.selectSearchBoardList(board);
	}
	
	@Override
	public int getSearchBoardListCnt(Board board) throws SQLException {
		return boardMapper.selectSearchBoardListCnt(board);
	}
	
	@Override
	public List<Board> getBoardList(Board board) throws SQLException {
		return boardMapper.selectBoardList(board);
	}
	
	@Override
	public int getBoardListCnt(Board board) throws SQLException {
		return boardMapper.selectBoardListCnt(board);
	}
	
	@Override
	public Board getBoard(Board board) throws SQLException {
		return boardMapper.selectBoard(board);
	}
	
	@Override
	public Board getBoard(String boardId, int boardSeq) throws SQLException {
		BoardGroup bg = new BoardGroup();
		bg.setBoardId(boardId);
		bg = boardGroupMapper.selectBoardGroup(bg);
		Board bd = new Board();
		bd.setBoardNm(bg.getBoardNm());
		bd.setBoardSeq(boardSeq);
		return boardMapper.selectBoard(bd);
	}

	@Override
	public List<BoardAttach> getBoardAttachList(BoardAttach boardAttach)
			throws SQLException {
		return boardAttachMapper.selectBoardAttachList(boardAttach);
	}

	@Override
	public BoardAttach getBoardAttach(int fileId) throws SQLException {
		return boardAttachMapper.selectBoardAttach(fileId);
	}

	@Override
	public void removeBoard(Board board) throws Exception {
		
		BoardGroup boardGroup = boardGroupService.getBoardGroup(board.getBoardId());
		board.setBoardNm(boardGroup.getBoardNm());
		
		Board bd = boardMapper.selectBoard(board);
		
		// Comment가 아닌 경우 하위 목록 삭제
		if(!bd.getBoardDiv().equals(Board.DIV_COMMENT)){
			// 하위 목록 답글의 Comment 삭제
			bd.setBoardNm(board.getBoardNm());
			boardMapper.procUnderIdLevel(bd); 
			List<Board> boardList = boardMapper.selectBoardTreeUnderStep(bd); // 하위목록 가져오기
			boardMapper.deleteTempTreeUnder();
			// 하위 목록 첨부파일 삭제
			for (Board bod : boardList) {
				// 실제 첨부파일 삭제
				BoardAttach boardAttach = new BoardAttach();
				boardAttach.setBoardId(board.getBoardId());
				boardAttach.setBoardSeq(bod.getBoardSeq());
				List<BoardAttach> boardAttachList = boardAttachMapper.selectBoardAttachList(boardAttach);
				
				// 첨부파일 목록도 삭제
				boardAttachMapper.deleteBoardAttach(boardAttach);
				
				// 파일 삭제
				for (BoardAttach ba : boardAttachList) {
					File file = new File(ba.getRealFilePath());
					if (file.exists()){
						file.delete();
					}
				}
				Path path = Paths.get(baseFilePath + "/" + uploadFilePath + "/" + board.getBoardId() + "/" + bod.getBoardSeq());
				FileControlUtil.deleteDirectory(path.toString());
				
				// 하위 게시글의 평가목록 삭제
				BoardAppraisal boardAppraisal = new BoardAppraisal();
				boardAppraisal.setBoardAppraisalNm(boardGroup.getBoardAppraisalNm());
				boardAppraisal.setBoardSeq(bod.getBoardSeq());
				boardAppraisalService.removeBoardAppraisal(boardAppraisal);

				// 하위 게시글의 댓글 삭제
				BoardComment boardComment = new BoardComment();
				boardComment.setBoardCommentNm(boardGroup.getBoardCommentNm());
				boardComment.setBoardSeq(bod.getBoardSeq());
				boardCommentService.removeBoardComment(boardComment);
				
				// 하위 게시글 삭제
				bod.setBoardNm(board.getBoardNm());
				boardMapper.deleteBoard(bod);
			}
		}
		
		// 게시글의 평가목록 삭제
		BoardAppraisal boardAppraisal = new BoardAppraisal();
		boardAppraisal.setBoardAppraisalNm(boardGroup.getBoardAppraisalNm());
		boardAppraisal.setBoardSeq(board.getBoardSeq());
		boardAppraisalService.removeBoardAppraisal(boardAppraisal);
		
		// 게시글의 댓글 삭제
		BoardComment boardComment = new BoardComment();
		boardComment.setBoardCommentNm(boardGroup.getBoardCommentNm());
		boardComment.setBoardSeq(board.getBoardSeq());
		boardCommentService.removeBoardComment(boardComment);
		
		// 실제 첨부파일 삭제
		BoardAttach boardAttach = new BoardAttach();
		boardAttach.setBoardId(board.getBoardId());
		boardAttach.setBoardSeq(board.getBoardSeq());
		List<BoardAttach> boardAttachList = boardAttachMapper.selectBoardAttachList(boardAttach);

		// 첨부파일 목록도 삭제
		boardAttachMapper.deleteBoardAttach(boardAttach);

		// 파일 삭제
		for (BoardAttach ba : boardAttachList) {
			File file = new File(ba.getRealFilePath());
			if (file.exists()){
				file.delete();
			}
		}
		Path path = Paths.get(baseFilePath + "/" + uploadFilePath + "/" + board.getBoardId() + "/" + board.getBoardSeq());
		FileControlUtil.deleteDirectory(path.toString());
		
		// 게시글 삭제
		boardMapper.deleteBoard(board);
	}

	@Override
	public void removeBoardAttachFile(int fileId) throws Exception {
		
		BoardAttach ba = boardAttachMapper.selectBoardAttach(fileId);
		// 첨부파일 목록도 삭제
		boardAttachMapper.deleteBoardAttach(ba);
		
		// 파일 삭제
		File file = new File(ba.getRealFilePath());
		if (file.exists()){
			file.delete();
		}
		
		// 파일이 없으면 디렉토리 삭제
		String spath = baseFilePath + "/" + uploadFilePath + "/" + ba.getBoardId() + "/" + ba.getBoardSeq();
		String path = Paths.get(spath).toString();
		if (!FileControlUtil.isExistFileInDirectory(path)){
			FileControlUtil.deleteDirectory(path);
		}
	}

	@Override
	public void updateNullBoardUserId(String userId) throws SQLException {
		
		BoardGroup boardGroup = new BoardGroup();
		boardGroup.setUseYn(null);
		
		List<BoardGroup> boardGroupList =  boardGroupMapper.selectBoardGroupList(boardGroup);
		for (BoardGroup bg : boardGroupList) {
			Board board = new Board();
			board.setUserId(userId);
			board.setBoardNm(bg.getBoardNm());
			boardMapper.updateNullBoardUserId(board);
			BoardComment boardComment = new BoardComment();
			boardComment.setBoardCommentNm(bg.getBoardCommentNm());
			boardComment.setUserId(userId);
			boardCommentMapper.updateNullBoardCommentUserId(boardComment);
		}
	}

	@Override
	public void modShowCountPlus(Board board) throws SQLException {
		boardMapper.updateShowCountPlus(board);
	}

	@Override
	public void modRecomCountPlus(Board board) throws SQLException {
		boardMapper.updateRecomCountPlus(board);
		
	}

	@Override
	public int getHotBoardListCnt(Board board) throws SQLException {
		
		return boardMapper.selectHotBoardListCnt(board);
	}

	@Override
	public List<Board> getHotBoardList(Board board) throws SQLException {
		return boardMapper.selectHotBoardList(board);
	}

	@Override
	public int getRecentBoardListCnt(Board board) throws SQLException {
		return boardMapper.selectRecentBoardListCnt(board);
	}

	@Override
	public List<Board> getRecentBoardList(Board board) throws SQLException {
		return boardMapper.selectRecentBoardList(board);
	}

}
