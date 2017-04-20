package com.mu.common.app;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.mu.common.utils.Log;

public class SystemInfo {
	
	/**
	 * 获取项目访问根路径
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getBasePath(HttpServletRequest request) throws UnknownHostException {
		Map<String, Object> systemInfoMap = getSystemMessage(request);
		String basePath = "http://" + systemInfoMap.get("ip").toString()
				+ ":" + systemInfoMap.get("localPort")
				+ systemInfoMap.get("contextPath") + "/";
		return basePath;
	}
	
	/**
	 * 获取项目真实路径。
	 * @param request
	 * @return
	 * @author mutian
	 */
	public static String getContextRealPath(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath("/");
		return realPath;
	}

	/**
	 * 获取一些系统信息
	 * 
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static Map<String, Object> getSystemMessage(HttpServletRequest request)
			throws UnknownHostException {
		Map<String, Object> map = new HashMap<>();
		map.put("userAgent", request.getHeader("User-Agent")); // 就是取得客户端的系统版本
		map.put("remoteAddr", request.getRemoteAddr()); // 取得客户端的IP
		map.put("remoteHost", request.getRemoteHost()); // 取得客户端的主机名
		map.put("remotePort", request.getRemotePort()); // 取得客户端的端口
		map.put("remoteUser", request.getRemoteUser()); // 取得客户端的用户
		map.put("localAddr", request.getLocalAddr()); // 取得本地IP
		map.put("localPort", request.getLocalPort()); // 取得本地端口
		map.put("contextPath", request.getContextPath()); // 项目名称
		InetAddress inet = InetAddress.getLocalHost();
		map.put("ip", inet.getHostAddress());
		return map;
	}
}
