package co.jufeng.json;

import java.io.Writer;

public interface JSONStreamAware {
	
	public Writer write(Writer writer) throws JSONException;
	
	public Writer write(Writer writer, int indentFactor, int indent) throws JSONException;
	
	public String toJSONString(int indentFactor) throws JSONException;

}
