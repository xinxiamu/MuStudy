package com.ymu.dao.mybatis.mapper;

import com.ymu.model.UserDetails;

public interface UserDetailsMapper {

	UserDetails selectUserDetailsById(long id);
}
