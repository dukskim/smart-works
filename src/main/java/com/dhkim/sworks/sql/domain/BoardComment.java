package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class BoardComment extends BaseCond implements Serializable {

	private static final long serialVersionUID = -8304759938686404794L;

	private String boardCommentNm;
	private String boardId;
	private String nickNm;
	
	private int commentSeq = -1;
	private int boardSeq = -1;	
	private String userId; 
	private String commentContent;
	private Date regDt;
	
	public int getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
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
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getBoardCommentNm() {
		return boardCommentNm;
	}
	public void setBoardCommentNm(String boardCommentNm) {
		this.boardCommentNm = boardCommentNm;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getNickNm() {
		return nickNm;
	}
	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}

}
