package com.j8.string;

public enum TaskStatus {
	All("全部(仅用于查询)"),
	waitDistribution("待分配");

	private final String value;

	TaskStatus(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
