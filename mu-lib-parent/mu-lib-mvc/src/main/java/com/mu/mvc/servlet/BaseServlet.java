package com.mu.mvc.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mu.mvc.RequestUtil;

public abstract class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5138486958171800881L;

	/**
	 * servlet访问接口入口
	 * @param req
	 * @param resp
	 * @param par	客户端上传简单参数
	 * @throws ServletException
	 * @throws IOException
	 */
	public abstract void go(HttpServletRequest req, HttpServletResponse resp,
			Map<String, String> par) throws ServletException, IOException;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		go(req, resp, RequestUtil.setPar2Map(req));
	}
}
