package com.didispace.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.DSDemoService;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/5/19 下午1:27.
 * @blog http://blog.didispace.com
 */
@RestController
public class HelloController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	DSDemoService dsDemoService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
    	logger.error("---hello web");
        return "Hello " + name;
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam String a) {
    	logger.error("---show web");
        return "show:: " + a;
    }
    
    @RequestMapping(value = "/testDS", method = RequestMethod.GET)
    @ResponseBody
    public String testDS() { 
        return dsDemoService.test("木头人");
    }
   

}
