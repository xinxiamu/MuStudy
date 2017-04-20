package com.mu.mvc.protocol;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mu.utils.StringUtils;
import com.mu.utils.json.FastJsonUtils;
import com.mu.utils.logger.Log;

/**
 * 验证http协议是否正确
 * 
 * @author Administrator
 * 
 */
@Component
public class HttpProtocolDetect {

	public final static String heads = "heads";
	public final static String version = "version";
	public final static String verification = "verification";
	public final static String msvalidate = "msvalidate";
	public final static String metaCharset = "metaCharset"; // 不比对这个

	/**
	 * 验证传输协议正确性
	 * 
	 * @param headsValue
	 *            如：{"metaCharset": "utf-8","msvalidate":
	 *            "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo","verification":
	 *            "5532f353892ad86095cb538ab988fb55","version": "V1.0"}
	 * @return
	 */
	public static boolean detectProtocol(String headsValue,
			HttpProtocol httpProtocol) {
		Log.println("客户端协议头：" + headsValue);
		Log.println("服务端协议头：" + getServerHeads(httpProtocol));
		boolean flg = true;
		if (StringUtils.isEmpty(headsValue)) {
			flg = false;
		}
		if (!FastJsonUtils.getJValueStr(headsValue, version, "").equals(
				httpProtocol.getVersion())
				|| !FastJsonUtils.getJValueStr(headsValue, verification, "")
						.equals(httpProtocol.getVerification())
				|| !FastJsonUtils.getJValueStr(headsValue, msvalidate, "")
						.equals(httpProtocol.getMsvalidate())) {
			flg = false;
		}
		Log.println(flg ? "-------一致，通过检测" : "------不一致，协议出错了");
		return flg;
	}

	/**
	 * 服务端自定义协议头
	 * 
	 * @return
	 * @author mutian
	 */
	private static String getServerHeads(HttpProtocol httpProtocol) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(version, httpProtocol.getVersion());
		jsonObject.put(verification, httpProtocol.getVerification());
		jsonObject.put(msvalidate, httpProtocol.getMsvalidate());
		jsonObject.put(metaCharset, httpProtocol.getMetaCharset());
		String jsonStr = FastJsonUtils.obj2JSONStr(jsonObject);
		return jsonStr;
	}

	public static void main(String[] args) {
		HttpProtocol httpProtocol = new HttpProtocol();
		httpProtocol.setVersion("V1.0");
		httpProtocol.setMetaCharset("utf-8");
		httpProtocol
				.setMsvalidate("-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo");
		httpProtocol.setVerification("5532f353892ad86095cb538ab988fb55");

		// 模拟构造客户端头参数
		Map<String, String> map = new HashMap<String, String>();
		map.put(version, "V1.0");
		map.put(msvalidate, "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo");
		map.put(verification, "5532f353892ad86095cb538ab988fb55");
		map.put(metaCharset, "utf-8");
		String clientPar = FastJsonUtils.obj2JSONStr(map);

		detectProtocol(clientPar, httpProtocol);
	}

}
