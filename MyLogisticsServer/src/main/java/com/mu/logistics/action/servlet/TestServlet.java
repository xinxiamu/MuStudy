package com.mu.logistics.action.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/test")
public class TestServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1480889982938278071L;

	@Override
	protected void service(HttpServletRequest rq, HttpServletResponse rp)
			throws ServletException, IOException {
		super.service(rq, rp);
		rp.getWriter().println("你老妹子");
	}

}
