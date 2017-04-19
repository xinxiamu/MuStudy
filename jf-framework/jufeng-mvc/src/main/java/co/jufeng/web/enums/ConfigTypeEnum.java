package co.jufeng.web.enums;

public enum ConfigTypeEnum {
	
	PROPERTIES("properties"), JSON("json"), XML("xml");
	
	public final String value;

	public String getValue() {
		return value;
	}
	
	ConfigTypeEnum(String value) {
		this.value = value;
	}

}