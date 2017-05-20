package com.didispace;

import org.springframework.stereotype.Service;

@Service
public class DSDemoService {
	
	@DSInject(value="ds注入")
	public String test(String str) {
		System.err.println(str);
		return str + "返回";
	}
}
