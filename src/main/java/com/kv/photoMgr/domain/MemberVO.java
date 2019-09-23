package com.kv.photoMgr.domain;

import java.util.Date;

public class MemberVO {

	private String memberid;
	private String name;
	private String password;
	private String tel;
	private String email;
	private Date regdate;
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberVO [memberid=" + memberid + ", name=" + name + ", password=" + password + ", tel=" + tel
				+ ", email=" + email + ", regdate=" + regdate + "]";
	}
	
	
}
