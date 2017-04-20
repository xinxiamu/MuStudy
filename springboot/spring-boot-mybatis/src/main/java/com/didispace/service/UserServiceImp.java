package com.didispace.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SqlSession sqlSession; 

	@Override
	public int insert(User user) {
		int i = userMapper.insert(user.getName(), user.getAge(),user.getNicName());
//		long a = 3/0;	//异常回滚
		return i;
	}
	

}
