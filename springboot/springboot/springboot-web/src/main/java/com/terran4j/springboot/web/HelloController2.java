package com.terran4j.springboot.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController2 {

	@Autowired
	private HelloService helloService;
	
	// URL示例：  http://localhost:8080/hello2/path/terran4j
	@RequestMapping(value = "/hello2/path/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String sayHelloByPath(@PathVariable("name") String name) {
		return helloService.hello(name);
	}
	
	// URL示例：  http://localhost:8080/hello2/map/terran4j
	@RequestMapping(value = "/hello2/map/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sayHello2Map(@PathVariable("name") String name) {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", helloService.hello(name));
		result.put("name", name);
		return result;
	}
	
	// URL示例：  http://localhost:8080/hello2/param?name=terran4j
	@RequestMapping(value = "/hello2/param", method = RequestMethod.GET)
	@ResponseBody
	public String sayHelloByParam(@RequestParam("name") String name) {
		return helloService.hello(name);
	}
	
	@RequestMapping(value = "/hello2/param-allow-null", method = RequestMethod.GET)
	@ResponseBody
	public String sayHelloByParamAllowNull(@RequestParam(value = "name", defaultValue = "") String name) {
		return helloService.hello(name);
	}
	
}
