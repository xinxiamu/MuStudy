package com.logistics.app.action.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONObject;
import com.logistics.app.exception.AppBaseExceptionCallback;
import com.mu.mvc.ConstantMvc;
import com.mu.mvc.base.BaseController;
import com.mu.mvc.exceptions.HttpProtocolException;
import com.mu.mvc.protocol.HttpProtocol;
import com.mu.mvc.protocol.HttpProtocolDetect;
import com.mu.utils.factory.PropertiesFactory;
import com.mu.utils.json.FastJsonUtils;
import com.mu.utils.logger.Log;

/**
 * 
 * @类描述：app controller接口父类。app接口的一些共用方法。
 * 
 * @创建人：mt
 * @创建时间：2014年9月10日上午9:48:04
 * @修改人：Administrator
 * @修改时间：2014年9月10日上午9:48:04
 * @修改备注：
 * @version v1.0
 * @Copyright
 * @mail 932852117@qq.com
 */
@Controller
public class AppBaseController extends BaseController {
	
	@Resource
	protected AppBaseExceptionCallback appBaseExceptionCallback;

	/**
	 * @RequestMapping注解的方法的入口。
	 * @throws HttpProtocolException
	 */
	@ModelAttribute
	public void init(HttpServletRequest request) throws HttpProtocolException {
		Log.println("-----------BaseController-init");

		if (isCheckProtocol() && !checkProtocol(request)) {
			throw new HttpProtocolException();
		}
	}

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	public void ininBinder(WebDataBinder binder) {
		Log.println("-----------ininBinder");
	}

	/**
	 * 获取app接口请求简单类型请求参数，不包括协议信息。
	 * <p>
	 * 请求参数格式： jsonString={"parameters": {"currentPage": "1","pageSize":
	 * "10","brandType": "1"},"heads": {"metaCharset": "utf-8","msvalidate":
	 * "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo","verification":
	 * "5532f353892ad86095cb538ab988fb55","version": "V1.0"}}
	 * </P>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getAppParSimpleMap(HttpServletRequest request) {
		Map<String, String> parMap = getParSimpleMap(request);
		Log.println("请求参数：" + parMap.toString());
		if (parMap.isEmpty()) {
			return new HashMap<>();
		}
		String parStr = parMap.get(ConstantMvc.REQUEST_KEY);
		JSONObject parJsonObj = FastJsonUtils.jSONStr2Json(parStr);

		Map<String, Object> appPars = FastJsonUtils.toJavaObje(
				parJsonObj.getString(ConstantMvc.REQUEST_PAR_KEY), Map.class);
		return appPars;
	}

	/**
	 * 获取app接口请求协议信息。heads部分
	 * <p>
	 * 请求参数格式： jsonString={"parameters": {"currentPage": "1","pageSize":
	 * "10","brandType": "1"},"heads": {"metaCharset": "utf-8","msvalidate":
	 * "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo","verification":
	 * "5532f353892ad86095cb538ab988fb55","version": "V1.0"}}
	 * </P>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getAppHeadsPar(HttpServletRequest request) {
		String fullParStr = request.getParameter(ConstantMvc.REQUEST_KEY);
		JSONObject parJsonObj = FastJsonUtils.jSONStr2Json(fullParStr);
		String headsParStr = parJsonObj == null ? null : parJsonObj
				.getString(HttpProtocolDetect.heads);
		if (headsParStr == null || headsParStr.equals("")) {
			return "";
		}
		return headsParStr;
	}

	/**
	 * 检测头协议是否和服务端的一致。
	 * 
	 * @param heads
	 * @return 一致返回true，表示通过检测
	 * @throws Exception
	 * @throws HttpProtocolException
	 */
	public boolean checkProtocol(HttpServletRequest request) {
		String hSt = getAppHeadsPar(request);
		
		HttpProtocol httpProtocol = new HttpProtocol();
		httpProtocol.setVersion(PropertiesFactory.getPropertyString("version"));
		httpProtocol.setMetaCharset(PropertiesFactory.getPropertyString("metaCharset"));
		httpProtocol
				.setMsvalidate(PropertiesFactory.getPropertyString("msvalidate"));
		httpProtocol.setVerification(PropertiesFactory.getPropertyString("verification"));

		return HttpProtocolDetect.detectProtocol(hSt, httpProtocol);
	}

	@Override
	public boolean isCheckProtocol() {
		return true;
	}

}
