package com.didispace.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.domain.User;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {
	
	@RequestMapping("/")
    public User user() {
		User user = new User();
		user.setAge(12);
        return user;
    }

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

}