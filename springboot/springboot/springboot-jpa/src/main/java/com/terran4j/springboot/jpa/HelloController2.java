package com.terran4j.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {

	@Autowired
	private UserRepository userRepository;

	// URL示例： http://localhost:8080/users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findByAgeRange(
			@RequestParam(value = "minAge", defaultValue = "1") int minAge,
			@RequestParam(value = "maxAge", defaultValue = "999") int maxAge) {

		List<User> users = userRepository.findByAgeRange(minAge, maxAge);

		return users;
	}

}
