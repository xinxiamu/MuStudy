package com.didispace.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.didispace.domain.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

	@Results({ @Result(property = "name", column = "name"),
			@Result(property = "age", column = "age") })
	@Select("SELECT name, age FROM user")
	List<User> findAll();

	@Insert("INSERT INTO user(name, age,nic_name) VALUES(#{name}, #{age},#{nic_name})")
	int insert(@Param("name") String name, @Param("age") Integer age,
			@Param("nic_name") String nicName);

	@Update("UPDATE user SET age=#{age} WHERE name=#{name}")
	void update(User user);

	@Delete("DELETE FROM user WHERE id =#{id}")
	void delete(Long id);

	@Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
	int insertByUser(User user);

	@Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
	int insertByMap(Map<String, Object> map);

}