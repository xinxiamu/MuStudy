package com.mu.logistics.action.controller.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.mu.common.action.BaseController;

@Controller
@RequestMapping(value = "uploadFile")
public class UploadFileController extends BaseController {

	@Override
	public boolean isCheckProtocol() {
		return false;
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileMultiple(HttpServletRequest request) {
		Map<String, String> map = getParMultipartMap(request);
		return JSONUtils.toJSONString(map);
	}

	

}
