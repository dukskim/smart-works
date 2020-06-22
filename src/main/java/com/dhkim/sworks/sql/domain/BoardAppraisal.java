package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class BoardAppraisal implements Serializable {

	private static final long serialVersionUID = 961293295708074124L;

	public static final String RECOMMEND = "1"; 

	private String boardAppraisalNm;
	
	private int boardSeq = -1;
	private String userId;
	private String appraisalType;
	private Date regDt;
	
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
	
	public String getAppraisalType() {
		return appraisalType;
	}
	
	public void setAppraisalType(String appraisalType) {
		this.appraisalType = appraisalType;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getBoardAppraisalNm() {
		return boardAppraisalNm;
	}

	public void setBoardAppraisalNm(String boardAppraisalNm) {
		this.boardAppraisalNm = boardAppraisalNm;
	}

}
