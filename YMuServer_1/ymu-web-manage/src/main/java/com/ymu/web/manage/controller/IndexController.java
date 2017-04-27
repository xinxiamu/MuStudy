package com.ymu.web.manage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ymu.web.manage.dto.Company;

@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = { "/", "/index.do" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(String bg, String abcd) {
		return "index";
	}

	@RequestMapping("/get")
	public void get(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		String callbackFunName = req.getParameter("callbackparam");// 得到js函数名称
		System.out.println("---callbackFunName:" + callbackFunName);
		try {
			res.getWriter().write(callbackFunName + "({name:\"John\"})"); // 返回jsonp数据
//			res.getWriter().write(callbackFunName + "([{name:\"John\"}])"); // 返回jsonp数据
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ajax")
	public void ajax(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain");
		try {
			res.getWriter().write("{name:\"John\"}"); // 返回jsonp数据
//			res.getWriter().write(callbackFunName + "([{name:\"John\"}])"); // 返回jsonp数据
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * spring mvc跨域请求解决方法。
	 * @param callbackparam
	 * @return
	 */
	@RequestMapping("/getJsonp")
	@ResponseBody
	public JSONPObject getJsonp(String callbackparam) {
		Company company = new Company();
		company.setAddress("广州天河华景软件园");
		company.setEmail("123456@qq.com");
		company.setName("广州讯动网络可以有限公司");
		company.setPhone("12345678912");
		return new JSONPObject(callbackparam, company);
	}

}
