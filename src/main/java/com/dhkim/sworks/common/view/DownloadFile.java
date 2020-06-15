/**
 * FileName : DownloadView.java
 * Comment :
 * 
 * @version : 1.0
 * @author : Kim Deok Hyun
 * @date : 2012. 5. 14.
 */
package com.dhkim.sworks.common.view;

import java.io.File;

public class DownloadFile {

	private File file;
	private String realFileName;
	private String orgFileName;
	
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	
	
}
