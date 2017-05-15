package com.didispace.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ResourceUtils;
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
		System.out.println("abc");
		User user = new User();
		user.setAge(12);
		user.setName("ab");
		return user;
	}

	@RequestMapping("/hello")
	public String index(HttpServletRequest request) throws IOException {

		// 测试读取路径
		File f = ResourceUtils.getFile("classpath:a/a.txt");
		FileReader a = new FileReader(f);
		try (BufferedReader br = new BufferedReader(a)) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Hello World";
	}

}