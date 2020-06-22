package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class BoardAttach implements Serializable {
	
	private static final long serialVersionUID = -5607358346038181732L;
	
	private int fileId = -1;
	private String fileNm;
	private String realFilePath;
	private int boardSeq;
	private String boardId;
	private Date regDt;
	
	public int getFileId() {
		return fileId;
	}
	
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
	public String getFileNm() {
		return fileNm;
	}
	
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	
	public String getRealFilePath() {
		return realFilePath;
	}
	
	public void setRealFilePath(String realFilePath) {
		this.realFilePath = realFilePath;
	}
	
	public int getBoardSeq() {
		return boardSeq;
	}
	
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
}
