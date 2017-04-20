package com.mu.common.enums;

/**
 * 数据源枚举
 * 
 * @author ZhengJianYan
 * @see CreateDate: 2013-8-12
 * @see UpdateName:
 * @see UpdateDate:
 * @see Copyright:Linan
 * @see QQ:375273058
 * @see <a href="mailto:13822119203@139.com">Email</a>
 * @since 1.0
 * @version V1.0
 */
public enum DataSource {

	/**
	 * IWL
	 */
	IWL {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	LINAN {
		public String toMatchString(String pattern) {
			return pattern;
		}
	};

	public abstract String toMatchString(String pattern);

}
