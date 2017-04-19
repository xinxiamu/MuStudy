package co.jufeng.web.enums;

public enum OutTypeEnum {
	
	TEXT("text/plain"), HTML("text/html"), XML("text/xml"), JSON("application/json");
	
	public final String value;

	public String getValue() {
		return value;
	}
	
	OutTypeEnum(String value) {
		this.value = value;
	}

}
