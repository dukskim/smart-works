package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class BoardGroup extends BaseCond implements Serializable {
	
	private static final long serialVersionUID = 5228602538115963488L;
	
	public static final String SHOW_MAIN_IMAGE = "1"; 
	public static final String SHOW_MAIN_RECOMMAND_BOARD_MONTH = "2"; 
	public static final String SHOW_MAIN_RECOMMAND_BOARD_ALL = "3"; 
	public static final String SHOW_MAIN_RECENT_BOARD = "4"; 
	
	private String boardNm;
	private String boardId;
	private String boardKor;
	private String boardDetail;
	private String boardAppraisalNm;
	private String boardAppraisalYn;
	private String boardCommentNm;
	private String boardCommentYn;
	private String showMainYn;
	private String category;
	private String categoryNm;
	private int sortSeq = -1;
	private String useYn;
	private Date regDt;
	
	public BoardGroup() {
		this.sortField = "SORT_SEQ";
		this.sortValue = "ASC";
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public int getSortSeq() {
		return sortSeq;
	}
	
	public void setSortSeq(int sortSeq) {
		this.sortSeq = sortSeq;
	}
	
	public String getBoardNm() {
		return boardNm;
	}
	
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	
	public String getBoardKor() {
		return boardKor;
	}
	
	public void setBoardKor(String boardKor) {
		this.boardKor = boardKor;
	}
	
	public String getBoardDetail() {
		return boardDetail;
	}
	
	public void setBoardDetail(String boardDetail) {
		this.boardDetail = boardDetail;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getUseYn() {
		return useYn;
	}
	
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	
	public String getBoardAppraisalNm() {
		return boardAppraisalNm;
	}

	public void setBoardAppraisalNm(String boardAppraisalNm) {
		this.boardAppraisalNm = boardAppraisalNm;
	}

	public String getBoardAppraisalYn() {
		return boardAppraisalYn;
	}

	public void setBoardAppraisalYn(String boardAppraisalYn) {
		this.boardAppraisalYn = boardAppraisalYn;
	}

	public String getShowMainYn() {
		return showMainYn;
	}

	public void setShowMainYn(String showMainYn) {
		this.showMainYn = showMainYn;
	}

	public String getBoardCommentNm() {
		return boardCommentNm;
	}

	public void setBoardCommentNm(String boardCommentNm) {
		this.boardCommentNm = boardCommentNm;
	}

	public String getBoardCommentYn() {
		return boardCommentYn;
	}

	public void setBoardCommentYn(String boardCommentYn) {
		this.boardCommentYn = boardCommentYn;
	}
	
}
