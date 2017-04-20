package com.logistics.persist.accesser.enums;

/**
 * 数据源枚举
 */
public enum DataSource {

	/**
	 * lg
	 */
	LG {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	/**
	 * log_log
	 */
	LG_LOG {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	/**
	 * lg_dictionary
	 */
	LG_DICTIONARY {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	A88 {
		public String toMatchString(String pattern) {
			return pattern;
		}
	};

	public abstract String toMatchString(String pattern);

}
