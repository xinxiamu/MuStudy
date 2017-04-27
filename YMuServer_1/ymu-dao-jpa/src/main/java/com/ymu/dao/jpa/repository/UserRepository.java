package com.ymu.dao.jpa.repository;

import com.ymu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameAndAge(String userName, Integer age);

//    @Query("from User u where u.name=:name")
//    User findUser(@Param("name") String name);
}
