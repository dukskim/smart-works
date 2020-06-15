/**
 * FileName : Test.java
 * Comment :
 * 
 * @version : 1.0
 * @author : 김덕현
 * @date : 2012. 2. 7.
 */
package com.dhkim.sworks.sql.domain;

import java.io.Serializable;

public class Test implements Serializable {
	
	private static final long serialVersionUID = -6013795119103355293L;
	
	private int aaa;
	private String bbb;
	
	public int getAaa() {
		return aaa;
	}
	
	public void setAaa(int aaa) {
		this.aaa = aaa;
	}
	
	public String getBbb() {
		return bbb;
	}
	
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	
}
