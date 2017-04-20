package com.mu.mvc.protocol;

public interface IHttpProtocol {
	/**
	 * 验证传输协议正确性
	 * 
	 * @param headsValue
	 *            如：{"metaCharset": "utf-8","msvalidate":
	 *            "-michJB8aokthZhSsY3KIyd7TW9tQ2jSXI_87qveZpo","verification":
	 *            "5532f353892ad86095cb538ab988fb55","version": "V1.0"}
	 * @return
	 */
	public boolean detectProtocol(String headsValue);
}
