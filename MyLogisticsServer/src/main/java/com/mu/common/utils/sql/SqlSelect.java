package com.mu.common.utils.sql;

/**
 * 
 * @类描述：查询语句的简单组装。
 *
 * @创建人：mt
 */
public class SqlSelect {
	
	StringBuffer sb = null;

	public SqlSelect() {
		sb = new StringBuffer("SELECT");
	}
	
	public void from(String from) {
		sb.append(" FROM " + from);
		sb.append(" WHERE 1=1");
	}
	
	/**
	 * 添加查询条件
	 * @param conditon 条件。如果是AND条件要加上AND
	 */
	public void addCondition(String conditon) {
		sb.append(" " + conditon + " ");
	}
	
	public String toString() {
		return sb.toString();
	}
}
