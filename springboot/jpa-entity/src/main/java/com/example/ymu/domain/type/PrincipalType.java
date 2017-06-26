package com.example.ymu.domain.type;

/**
 * 校长类型
 * @author Administrator
 *
 */
public enum PrincipalType {
	
	POSITIVE_LEVEL("正校长"),
	NEGATIVE_LEVEL("副校长");
	
	private String value;

	private PrincipalType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
