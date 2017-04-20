package com.didispace.web;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/5/19 下午1:27.
 * @blog http://blog.didispace.com
 */
@RestController
public class HelloController {
	
	private Logger logger = Logger.getLogger(getClass());

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
   

}
