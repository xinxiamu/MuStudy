package com.didispace.domain.p;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	List<UserDetails> findByUser(User user);  
	
	List<UserDetails> findByUserId(Long userId);

}
