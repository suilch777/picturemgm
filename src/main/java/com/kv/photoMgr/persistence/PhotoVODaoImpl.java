package com.kv.photoMgr.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.kv.photoMgr.domain.PhotoVO;

@Repository
public class PhotoVODaoImpl implements PhotoVODao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.kv.photoMgr.mappers.PhotoMapper";
	
	@Override
	public void register(PhotoVO photo) throws Exception {
		sqlSession.insert(namespace+".register", photo);

	}

}
