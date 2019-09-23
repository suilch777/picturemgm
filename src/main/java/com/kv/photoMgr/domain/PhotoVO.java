package com.kv.photoMgr.domain;

import java.util.Date;

public class PhotoVO {

	String memberid;
	String fullname;
	Date regdate;
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "PhotoVO [memberid=" + memberid + ", fullname=" + fullname + ", regdate=" + regdate + "]";
	}
	
	
	
	
}
