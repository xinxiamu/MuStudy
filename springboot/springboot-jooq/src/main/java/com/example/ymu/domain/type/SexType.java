package com.example.ymu.domain.type;

/**
 * 校长类型
 * @author Administrator
 *
 */
public enum SexType {
	
	MALE("男"),
	FEMALE("女");
	
	private String value;

	private SexType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
