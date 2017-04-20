package com.mu.logistics.action.protocol;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mu.common.utils.FastJsonUtils;
import com.mu.common.utils.Log;
import com.mu.common.utils.StringUtils;

/**
 * 验证http协议是否正确
 * 
 * @author Administrator
 * 
 */
@Component
public class HttpProtocol  {

	public final static  String heads = "heads";
	public final static String version = "V1.0";
	public final static String verification = "5532f353892ad86095cb538ab988fb55";
	public final static String msvalidate = "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo";
	public final static String metaCharset = "utf-8"; // 不比对这个
	
	/**
	 * 验证传输协议正确性
	 * 
	 * @param headsValue
	 *            如：{"metaCharset": "utf-8","msvalidate":
	 *            "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo","verification":
	 *            "5532f353892ad86095cb538ab988fb55","version": "V1.0"}
	 * @return
	 */
	public static boolean detectProtocol(String headsValue) {
		Log.println("客户端协议头：" + headsValue);
		Log.println("服务端协议头：" + getServerHeads());
		boolean flg = true;
		if (StringUtils.isEmpty(headsValue)) {
			flg = false;
		}
		if (!FastJsonUtils.getJValueStr(headsValue, "version", "")
				.equals(version)
				|| !FastJsonUtils.getJValueStr(headsValue, "verification", "")
						.equals(verification)
				|| !FastJsonUtils.getJValueStr(headsValue, "msvalidate", "")
						.equals(msvalidate)) {
			flg = false;
		}
		Log.println(flg ? "-------一致，通过检测" : "------不一致，协议出错了");
		return flg;
	}
	
	/**
	 * 服务端自定义协议头
	 * @return
	 * @author mutian
	 */
	public static String getServerHeads() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", version);
		jsonObject.put("verification", verification);
		jsonObject.put("msvalidate", msvalidate);
		jsonObject.put("metaCharset", metaCharset);
		String jsonStr = FastJsonUtils.obj2JSONStr(jsonObject);
		return jsonStr;
	}

	public static void main(String[] args) {
		String heads = getServerHeads();
		detectProtocol(heads);
	}

}
