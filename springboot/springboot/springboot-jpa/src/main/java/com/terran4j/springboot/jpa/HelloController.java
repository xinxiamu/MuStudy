package com.terran4j.springboot.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloService helloService;

	@Autowired
	private UserRepository userRepository;

	// URL示例： http://localhost:8080/hello/1
	@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(@PathVariable("id") Long id) {

		User user = userRepository.findOne(id);

		if (user == null) {
			return String.format("User NOT Found: %d", id);
		}

		String name = user.getLoginName();
		return helloService.hello(name);
	}

}
