package com.logistics.persist.accesser.enums;

/**
 * 连接池枚举
 * @author ZhengJianYan
 * @see CreateDate: 2013-8-12
 * @see UpdateName:
 * @see UpdateDate:
 * @see Copyright:Linan
 * @see QQ:375273058
 * @see <a href="mailto:13822119203@139.com">Email</a>
 * @since 1.0
 * @version	V1.0
 */
public enum ConnectionPool {

	/**
	 * Spring连接池
	 */
	SPRING {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},

	/**
	 * Druid连接池
	 */
	DRUID {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},	
	
	/**
	 * dbcp连接池
	 */
	DBCP {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	/**
	 * c3p0连接池
	 */
	C3P0 {
		public String toMatchString(String pattern) {
			return pattern;
		}
	};
	
	public abstract String toMatchString(String pattern);



}
