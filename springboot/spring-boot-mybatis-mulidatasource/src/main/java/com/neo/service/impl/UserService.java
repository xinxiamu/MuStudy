package com.neo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neo.entity.UserEntity;
import com.neo.mapper.mu.UserMapper;
import com.neo.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;	

	@Override
	public void saveMu(UserEntity user) {
		userMapper.insert(user);
	}
}
