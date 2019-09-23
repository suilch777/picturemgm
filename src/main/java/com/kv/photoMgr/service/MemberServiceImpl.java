package com.kv.photoMgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kv.photoMgr.domain.MemberVO;
import com.kv.photoMgr.persistence.MemberDao;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public String getTime() {
		
		return dao.getTime();
	}
	
		
	@Override
	public MemberVO selectMemberByIdAndPw(String userid, String userpw) {
		// TODO Auto-generated method stub
		return dao.selectMemberByIdAndPw(userid, userpw);
	}

	@Override
	public void register(MemberVO vo) throws Exception {
		dao.register(vo);
		
	}


	@Override
	public MemberVO readMember(String userid) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}


	@Override
	public MemberVO read(String memberid) {
		// TODO Auto-generated method stub
		return dao.selectMember(memberid);
	}


	@Override
	public void remove(String memberid) {
		dao.remove(memberid);
		
	}


	@Override
	public void modify(MemberVO member) {
		dao.update(member);
		
	}


	@Override
	public int idCheck(String memberid) {
		
		
		return dao.idCheck(memberid);
	}

}
