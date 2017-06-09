package com.j8.string;

public enum TaskStatus {
	All("全部"),
	waitDistribution("待分配");

	private final String value;

	TaskStatus(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
