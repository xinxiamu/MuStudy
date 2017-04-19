package co.jufeng.hibernate;

public enum ProjectionMode {

	COUNT {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},

	COUNT_DISTINCT {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	MAX {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	MIN {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	AVG {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	SUM {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	GROUP {
		public String toMatchString(String pattern) {
			return pattern;
		}
	},
	
	GROUP_COUNT {
		public String toMatchString(String pattern) {
			return pattern;
		}
	}
	;

	public abstract String toMatchString(String pattern);


}
