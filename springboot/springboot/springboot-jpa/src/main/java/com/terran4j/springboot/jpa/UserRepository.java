package com.terran4j.springboot.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 查询某年龄段范围之内的用户。
	 * 
	 * @param minAge
	 *            最小年龄。
	 * @param maxAge
	 *            最大年龄。
	 * @return
	 */
	@Query(nativeQuery = true, name = "User.findByAgeRange")
	List<User> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

}
