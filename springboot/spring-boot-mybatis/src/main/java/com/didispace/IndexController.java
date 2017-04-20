package com.didispace;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.domain.User;
import com.didispace.mapper.UserMapper;
import com.didispace.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserMapper userMapper;

	@Resource
	private UserService userService;

	@RequestMapping(value = "/")
	@ResponseBody
	public String index() {
		return "wlcome,hello world";
	}

	/**
	 * 查询会员
	 * 
	 * @return
	 */
	@RequestMapping(value = "/find-user-name")
	@ResponseBody
	public String findUserName() {
		User u = userMapper.findByName("AAA");
		System.out.println(u);
		return u.getName();
	}

	@RequestMapping(value = "/insert-user")
	@ResponseBody
	public String insert() {
		User user = new User();
		user.setAge(22);
		user.setNicName("木头");
		user.setName("长牧田");
		int i = userService.insert(user);
		return String.valueOf(i);
	}

}
