package com.mu.common.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.mu.common.utils.MapUtils;
import com.mu.common.utils.UploadFile;
import com.mu.common.utils.UploadFile.UploadFileCallBack;
import com.mu.logistics.action.controller.exception.AppBaseException;

@Controller
public abstract class BaseController {

	/**
	 * 是否开启检测自定义头协议。
	 * 
	 * @return 返回true开启。
	 */
	public abstract boolean isCheckProtocol();

	/**
	 * 获取请求参数。简单类型请求参数，即不能包含文件。
	 * 
	 * @param request
	 * @return
	 * @author mutian
	 * @throws Exception
	 */
	public Map<String, String> getParSimpleMap(HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = request.getParameter(name);
			param.put(name, value);
		}

		return param;
	}

	/**
	 * 获取简单参数以及保存上传文件。注意传参顺序，先传简单类型，再传文件
	 * 
	 * @param request
	 * @param fileSavePath 文件保存路径
	 * @return 返回map，包含保存文件在服务器的路径，以及普通参数。
	 * @author mutian
	 */
	public Map<String, String> getParMultipartMap(HttpServletRequest request) {
		final Map<String, String> param = new HashMap<String, String>();

		if (isMultipartRequest(request)) {
			try {
				UploadFile.saveFileFromClient(request,
						new UploadFileCallBack() {

							@Override
							public void parameters(Map<String, String> parsMap) {
								MapUtils.map1ToMap2(parsMap, param);
							}

							@Override
							public void filesURL(Map<String, String> filsURL) {
								MapUtils.map1ToMap2(filsURL, param);
							}
						});
			} catch (Exception e) {
				AppBaseException.throwsException(getClass(), e);
			}
		}
		return param;
	}

	/**
	 * 检测请求是否是复合（有文件请求）请求。
	 * 
	 * @param request
	 * @return 是复合请求，返回true
	 */
	public boolean isMultipartRequest(HttpServletRequest request) {
		boolean flg = false;
		String contentType = request.getContentType();
		if (contentType != null
				&& contentType.toLowerCase().startsWith("multipart/")) { // 有上传文件
			flg = true;
		}
		return flg;
	}

}
