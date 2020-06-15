/**
 * FileName : UserInfo.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 2. 17.
 */
package com.dhkim.sworks.sql.domain;

import java.io.Serializable;
import java.util.Date;

public class UserInfo extends BaseCond implements Serializable {
	
	private static final long serialVersionUID = 2210805047741988008L;
	
	private String userId;
	private String userNm;
	private String pwd;
	private String nickNm;
	private String email;
	private int point = -1;
	private int userLevel = -1;
	private String telNum;
	private String mobile;
	private String deptId;
	private String regStatus;
	private Date regDt;
	
	public String getNickNm() {
		return nickNm;
	}
	
	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserNm() {
		return userNm;
	}
	
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getUserLevel() {
		return userLevel;
	}
	
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getTelNum() {
		return telNum;
	}
	
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getDeptId() {
		return deptId;
	}
	
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getRegStatus() {
		return regStatus;
	}
	
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
}
