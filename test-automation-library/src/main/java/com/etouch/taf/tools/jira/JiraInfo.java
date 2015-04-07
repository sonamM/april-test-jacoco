package com.etouch.taf.tools.jira;

/**
 * This class stores information used to connect rally instance.
 * 
 * @author eTouch Systems Corporation
 * @version 1.0
 *
 */
public class JiraInfo {
	
	String url;
	String userName;
	String password;
	String issueUrl;
	//String auth;
	
	public JiraInfo(String url, String userName, String password) {
		super();
		this.url = url;
		this.issueUrl = issueUrl;
		this.userName = userName;
		this.password = password;
		//this.auth = auth;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getissueUrl() {
		return issueUrl;
	}
	public void setissueUrl(String issueUrl) {
		this.issueUrl = issueUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
/*	public String getauth() {
		return auth;
	}*/
	
}

