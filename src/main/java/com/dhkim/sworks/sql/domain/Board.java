/**
 * FileName : Board.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 3. 6.
 */
package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Board extends BaseCond implements Serializable {
	
	private static final long serialVersionUID = -3818141515804640369L;
	
	public static final String DIV_NORMAL = "1"; 
	public static final String DIV_REPLAY = "2"; 
	public static final String DIV_COMMENT = "3"; 
	
	private String boardNm;
	private String boardId;
	private String nickNm;
	private int commentCnt = 0;
	private int duringMonth = -1;
	private String boardCommentNm;
	
	private int boardSeq = -1;
	private String userId;
	private String title;
	private String boardDiv;
	private String boardContent;
	private int refBoardSeq = -1;
	private int showCnt = -1;
	private int recomCnt = -1;
	private int point = -1;
	private int boardFamily;
	private int boardFamilySort;;
	private int boardStep;
	private String delYn;
	private Date regDt;
	private MultipartFile[] attachFile;
	
	public String getBoardNm() {
		return boardNm;
	}
	
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public String getNickNm() {
		return nickNm;
	}

	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public int getRefBoardSeq() {
		return refBoardSeq;
	}
	
	public void setRefBoardSeq(int refBoardSeq) {
		this.refBoardSeq = refBoardSeq;
	}
	
	public int getBoardSeq() {
		return boardSeq;
	}
	
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBoardDiv() {
		return boardDiv;
	}
	
	public void setBoardDiv(String boardDiv) {
		this.boardDiv = boardDiv;
	}
	
	public String getBoardContent() {
		return boardContent;
	}
	
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	public int getShowCnt() {
		return showCnt;
	}
	
	public void setShowCnt(int showCnt) {
		this.showCnt = showCnt;
	}
	
	public int getRecomCnt() {
		return recomCnt;
	}
	
	public void setRecomCnt(int recomCnt) {
		this.recomCnt = recomCnt;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getDelYn() {
		return delYn;
	}
	
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
	public int getBoardFamily() {
		return boardFamily;
	}
	
	public void setBoardFamily(int boardFamily) {
		this.boardFamily = boardFamily;
	}
	
	public int getBoardStep() {
		return boardStep;
	}
	
	public void setBoardStep(int boardStep) {
		this.boardStep = boardStep;
	}
	
	public int getBoardFamilySort() {
		return boardFamilySort;
	}
	
	public void setBoardFamilySort(int boardFamilySort) {
		this.boardFamilySort = boardFamilySort;
	}
	
	public MultipartFile[] getAttachFile() {
		return attachFile;
	}
	
	public void setAttachFile(MultipartFile[] attachFile) {
		this.attachFile = attachFile;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getDuringMonth() {
		return duringMonth;
	}

	public void setDuringMonth(int duringMonth) {
		this.duringMonth = duringMonth;
	}

	public String getBoardCommentNm() {
		return boardCommentNm;
	}

	public void setBoardCommentNm(String boardCommentNm) {
		this.boardCommentNm = boardCommentNm;
	}

	
}
