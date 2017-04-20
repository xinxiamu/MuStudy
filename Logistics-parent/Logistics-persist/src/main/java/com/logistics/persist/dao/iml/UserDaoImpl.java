package com.logistics.persist.dao.iml;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.model.lg.user.User;
import com.logistics.persist.accesser.iml.CommonDaoImpl;
import com.logistics.persist.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonDaoImpl implements UserDao {

	@Override
	public void add(User user) {
		
	}

	@Override
	public void addBatch(List<User> users) {
		
	}

	@Override
	public Long getUserMaxIdA88() {
		return null;
	}

	@Override
	public Long getUserMaxId() {
		return null;
	}

}
