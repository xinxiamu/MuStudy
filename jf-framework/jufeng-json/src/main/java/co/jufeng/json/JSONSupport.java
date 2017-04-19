package co.jufeng.json;

public class JSONSupport implements JSONString{
	
	public void parse(String jsonString){
		new JSONObject(jsonString).toBean(this);
	}

	public JSONObject toJSON() {
		return new JSONObject(this);
	}
	
	@Override
	public String toJSONString() {
		return toJSON().toString();
	}

	public String toPrettyString() {
		return toJSON().toJSONString(4);
	}

	@Override
	public String toString() {
		return toJSONString();
	}
}
