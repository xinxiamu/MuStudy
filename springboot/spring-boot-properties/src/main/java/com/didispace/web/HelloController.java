package com.didispace.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.configuration.properties.My;
import com.didispace.service.SendMessage;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
public class HelloController {
	
	@Autowired
	private My my;

	@Resource
	private SendMessage sendMessage;

	@Value("${my.name}")
	private String myName;

	@RequestMapping("/")
	public String index() {
		return myName + sendMessage.send();
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World:" + my.getName();
	}

}