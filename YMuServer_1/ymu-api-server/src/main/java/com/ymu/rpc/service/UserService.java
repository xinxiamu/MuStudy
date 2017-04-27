package com.ymu.rpc.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymu.api.IUserDetailsService;
import com.ymu.api.IUserService;
import com.ymu.dao.jdbc.impl.UserDao;
import com.ymu.dao.jpa.repository.UserRepository;
import com.ymu.model.User;
import com.ymu.model.UserDetails;
import com.ymu.rpc.base.BaseService;
import com.ymu.vo.UserVo;

@Service("userService")
public class UserService extends BaseService implements IUserService {

	@Autowired
	private IUserDetailsService userDetailsService;

	@Autowired
	private UserDao UserDao;

	private UserRepository userRepository;

	@Override
	public User getById(long usrId) throws Exception {
		return UserDao.getById(usrId);
	}

	@Override
	public User getByUserName(@NotNull String userName) throws Exception {
		return UserDao.getByUserName(userName);
	}

	@Override
	public UserVo getUserAllInfoByUserId(long userId) throws Exception {
		return UserDao.getUserAllInfoByUserId(userId);
	}

	@Transactional
	@Override
	public UserVo saveUserAllInfo(UserVo userVo) throws Exception {
		User user = save(userVo.getUser());
		userVo.getUserDetails().setUserId(user.getId());
		UserDetails userDetails = userDetailsService.save(userVo
				.getUserDetails());
		userVo.setUser(user);
		userVo.setUserDetails(userDetails);
		return userVo;
	}

	@Override
	public User save(User user) throws Exception {
		return userRepository.save(user);
	}
}
