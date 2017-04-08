package com.didispace.domain.p;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findById(Long id);
	
	User findByName(String name);

}
