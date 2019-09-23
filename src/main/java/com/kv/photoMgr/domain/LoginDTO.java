package com.kv.photoMgr.domain;

public class LoginDTO {
	private String userid;
	private String username;
	private String userpw;
	
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "LoginDTO [userid=" + userid + ", username=" + username + ", userpw=" + userpw + "]";
	}
	
	}
	
	

