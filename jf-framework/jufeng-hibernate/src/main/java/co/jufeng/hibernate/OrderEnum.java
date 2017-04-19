package co.jufeng.hibernate;

public enum OrderEnum {
	
	DESC("desc"), ASC("asc");
	
	public final String value;

	public String getValue() {
		return value;
	}
	
	OrderEnum(String value) {
		this.value = value;
	}
}
