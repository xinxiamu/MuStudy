package com.mu.mvc.protocol;

public class HttpProtocol {

	public String version;
	
	public String verification;
	
	public String msvalidate;
	
	public String metaCharset; // 不比对这个

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getMsvalidate() {
		return msvalidate;
	}

	public void setMsvalidate(String msvalidate) {
		this.msvalidate = msvalidate;
	}

	public String getMetaCharset() {
		return metaCharset;
	}

	public void setMetaCharset(String metaCharset) {
		this.metaCharset = metaCharset;
	}
}
