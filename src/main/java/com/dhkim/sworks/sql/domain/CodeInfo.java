package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class CodeInfo extends BaseCond implements Serializable {

	private static final long serialVersionUID = -689046456879662632L;
	
	private String codeId;
	private String groupCode;
	private String codeNm;
	private String groupCodeNm;
	private String codeDetail;
	private int sortSeq;
	private String useYn;
	private Date regDt;
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getCodeNm() {
		return codeNm;
	}
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}
	public String getGroupCodeNm() {
		return groupCodeNm;
	}
	public void setGroupCodeNm(String groupCodeNm) {
		this.groupCodeNm = groupCodeNm;
	}
	public String getCodeDetail() {
		return codeDetail;
	}
	public void setCodeDetail(String codeDetail) {
		this.codeDetail = codeDetail;
	}
	public int getSortSeq() {
		return sortSeq;
	}
	public void setSortSeq(int sortSeq) {
		this.sortSeq = sortSeq;
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
	
}
