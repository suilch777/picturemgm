package com.kv.photoMgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kv.photoMgr.domain.PhotoVO;
import com.kv.photoMgr.persistence.PhotoVODao;




@Service
public class PhotoVOServiceImpl implements PhotoVOService {

	@Autowired
PhotoVODao dao;
	
	@Override
	public void register(PhotoVO photo) throws Exception {
		dao.register(photo);
		
	}

}
