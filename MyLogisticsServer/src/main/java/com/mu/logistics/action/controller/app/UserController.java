package com.mu.logistics.action.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mu.common.dao.iml.Accesser;
import com.mu.common.utils.Log;
import com.mu.logistics.action.controller.exception.AppBaseException;
import com.mu.logistics.service.IUserService;

@Controller
@RequestMapping(value = "app/user")
public class UserController extends AppBaseController {

	@Resource
	private Accesser accesser;

	@Resource
	private IUserService userService;

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody
	String login(HttpServletRequest request, HttpServletResponse response,
			Device device) {
		String deviceType = "unknown";
		if (device.isNormal()) {
			deviceType = "normal";
		} else if (device.isMobile()) {
			deviceType = "mobile";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		}

		Log.d(this, "debug登录的终端为：" + deviceType);

		Map<String, Object> parameters = null;
		parameters = getAppParSimpleMap(request);
		Log.println(parameters.toString());

		// 查询
		String sql = "SELECT top 10  wj_User,wj_UserPwd,wj_Type,wj_Sex,wj_Mobile,wj_Address,createtime FROM wj_User WHERE wj_Mobile IS NOT NULL AND wj_Mobile!='' ORDER BY createtime DESC";
		JdbcTemplate jdbcTemplate = accesser.getJdbcTemplateFactory()
				.getJdbcTemplateC3p0A88();
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

		Gson gson = new Gson();
		String aa = gson.toJson(resultList);

		return aa;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public @ResponseBody
	String register() {
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小鱼儿");
		map.put("age", 26);
		map.put("address", "广东省茂名市电白县电城镇");
		map.put("visit-device", "移动端app");
		gson.toJson(map);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("a", "你妹子");
		jsonObject.addProperty("b", "你哥");
		jsonObject.addProperty("c", "你奶奶的爷爷的奶奶的爷爷");

		return jsonObject.toString();
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public @ResponseBody
	String save() {
		int[] flg = null;
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			flg = userService.a88UserMoveToIwl();
			map.put("total", flg);
			map.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("status", -1);
		}
		String jsonStr = gson.toJson(map);
		return jsonStr;

	}

	
	@RequestMapping(value = "/saveHeadImg", method = RequestMethod.POST)
	public @ResponseBody
	String saveUserHeadPhoto(HttpServletRequest request) {
		return "";
	}

}
