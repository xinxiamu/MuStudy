package com.ymu.web.www.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ymu.infrastructure.servlet.BaseServlet;

@WebServlet(urlPatterns = "/ymu/myservlet", description = "Servlet的说明")
// 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.MyServlet2
public class ServletTest extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5379808740786215365L;

	@Override
	public void go(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("---------go");
	}

}
