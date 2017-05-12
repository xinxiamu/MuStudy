package com.neo.web;

import java.util.List;

import com.neo.mapper.mu.UserMapper;
import com.neo.mapper.test1.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.UserEntity;
import com.neo.mapper.test2.User2Mapper;
import com.neo.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private User1Mapper user1Mapper;

	@Autowired
	private User2Mapper user2Mapper;
	
	//------------mu
	
	@RequestMapping("/add-mu")
	public void saveMu(UserEntity user) {
		System.out.println("---req:");
//		UserEntity userEntity = new UserEntity;
		userService.saveMu(user);
	}
	
	@RequestMapping("/getMuUser")
	public UserEntity getMuUser(Long id) {
		return null;
	}
	
	//----------test

	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users = user1Mapper.getAll();
		return users;
	}

	@RequestMapping("/getUser")
	public UserEntity getUser(Long id) {
		UserEntity user = user2Mapper.getOne(id);
		return user;
	}

	@RequestMapping("/add")
	public void save(UserEntity user) {
		user2Mapper.insert(user);
	}

	@RequestMapping(value = "update")
	public void update(UserEntity user) {
		user2Mapper.update(user);
	}

	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		user1Mapper.delete(id);
	}

}