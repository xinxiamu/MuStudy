package com.ymu.dao.mybatis.mapper;

import com.ymu.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

//@Mapper	//在MyBatisConfig配置文件中已配置
public interface UserMapper {

	@Results({@Result(property = "userName",column = "user_name")})
	@Select("SELECT * FROM user WHERE id = #{id}")
	User selectUserById(long id);

	User selectById(long id);
}
