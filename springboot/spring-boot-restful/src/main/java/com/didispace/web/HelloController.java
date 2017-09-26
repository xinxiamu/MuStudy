package com.didispace.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.version.ApiVersion;

@RestController
@RequestMapping("/{version}/")
public class HelloController {
	
	/*@Value("${my.name}")
	private String myName;
	
	@Value("${server.port}")
	private String port;

	@RequestMapping("/")
	public User user() {
		User user = new User();
		user.setAge(12);
		user.setName(myName);
		return user;
	}*/
	
	//---------------- api版本管理 demo start ------------------//

	@RequestMapping("hello/")
    @ApiVersion(1)
	@ResponseBody
    public String hello(HttpServletRequest request){
        System.out.println("haha1..........");
        
        return "hello";
    }
    
    @RequestMapping("hello/")
    @ApiVersion(2)
    @ResponseBody
    public String hello2(HttpServletRequest request){
        System.out.println("haha2.........");
        
        return "hello";
    }
    
    @RequestMapping("hello/")
    @ResponseBody
    @ApiVersion(5)
    public String hello5(HttpServletRequest request){
        System.out.println("haha5.........");
        
        return "hello";
    }
    
  //---------------- api版本管理 demo end ------------------//
	
	@RequestMapping("/testResourceFileLoad")
	public String testResourceFileLoad() throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		// 测试读取路径
		File f = ResourceUtils.getFile("classpath:a/a.js");
		FileReader a = new FileReader(f);
		try (BufferedReader br = new BufferedReader(a)) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				sb.append(sCurrentLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deal(sb.toString());
	}
	
	
	private String deal(String s) {
		StringBuilder sb = new StringBuilder();
		System.out.println("---------:::" + s);
		char[] b = s.toCharArray();
		for (int i = 0; i < b.length; i++) {
			char bt = b[i];
			sb.append(bt);
			if (bt == '{' || bt == '}') {
				System.out.println("=======第一个" + bt);  
				sb.append("</br>");
			}  
			if (bt == ';') {
				sb.append("</br>");
			}
			
		}
		System.out.println("---" + sb.toString());
		return sb.toString();  
	}

}