package com.kv.photoMgr.persistence;

import java.util.List;

import com.kv.photoMgr.domain.MemberVO;


public interface MemberDao {
	public String getTime();
	public MemberVO selectMember(String userid);
	public List<MemberVO> selectAll();
	public void update(MemberVO vo);	
	public MemberVO selectMemberByIdAndPw(String userid, String userpw);
	public void register(MemberVO vo)throws Exception;
	public void remove(String memberid);
	public int idCheck(String memberid);
}
