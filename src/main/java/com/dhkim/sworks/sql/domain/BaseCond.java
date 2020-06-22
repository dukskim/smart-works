package com.dhkim.sworks.sql.domain;

public class BaseCond {
	
	// sort
	protected String sortField;
	protected String sortValue;
	
	// search
	protected String searchVal;
	protected String searchType;
	protected String searchGroup;
	// page
	protected boolean chkPaging = true;
	protected int firstNo = 1; // Row 번호
	protected int pageNo = 1; // 페이지번호
	protected int pageInqCnt = 15; // 페이지 당 게시물 수
	protected int showPageBlock = 3; // 보여줄 페이지 수
	
	// getter setter
	public int getFirstNo() {
		return firstNo;
	}
	
	public void setFirstNo(int firstNo) {
		this.firstNo = firstNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageInqCnt() {
		return pageInqCnt;
	}
	
	public void setPageInqCnt(int pageInqCnt) {
		this.pageInqCnt = pageInqCnt;
	}
	
	public String getSortField() {
		return sortField;
	}
	
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public String getSortValue() {
		return sortValue;
	}
	
	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchVal() {
		return searchVal;
	}
	
	public void setSearchVal(String searchVal) {
		this.searchVal = searchVal;
	}
	
	public String getSearchGroup() {
		return searchGroup;
	}
	
	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}
	
	public boolean isChkPaging() {
		return chkPaging;
	}
	
	public void setChkPaging(boolean chkPaging) {
		this.chkPaging = chkPaging;
	}
	
	public int getShowPageBlock() {
		return showPageBlock;
	}
	
	public void setShowPageBlock(int showPageBlock) {
		this.showPageBlock = showPageBlock;
	}
	
}
