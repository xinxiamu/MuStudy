package com.terran4j.springboot.jpa;

public enum UserStatus {

	/**
	 * 启用状态，此账号可以正常使用。
	 */
	enable, 
	
	/**
	 * 禁用状态，此账号不允许被使用。
	 */
	disable
}
