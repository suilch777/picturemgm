package com.kv.photoMgr.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kv.photoMgr.domain.MemberVO;


@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.kv.photoMgr.mappers.MemberMapper";

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne(namespace+".getTime");
	}
	
	@Override
	public MemberVO selectMember(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectMember", userid);
	}

	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectAll");
	}

	@Override
	public void update(MemberVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".update", vo);
	}

	

	@Override
	public MemberVO selectMemberByIdAndPw(String userid, String userpw) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("userpw", userpw);
		
		return sqlSession.selectOne(namespace+".selectMemberByIdAndPw",map );
	}

	@Override
	public void register(MemberVO vo) throws Exception {
		sqlSession.insert(namespace+".register", vo);
		
	}

	@Override
	public void remove(String memberid) {
		sqlSession.delete(namespace+".remove", memberid);
		
	}

	@Override
	public int idCheck(String memberid) {
		sqlSession.selectOne(namespace+".idCheck", memberid);
		return sqlSession.selectOne(namespace+".idCheck", memberid);
		
	}

}
