package com.ymu.dao.jpa.repository;

import com.ymu.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
