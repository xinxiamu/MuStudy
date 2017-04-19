package co.jufeng.json;

public class JSONNull {
	
	public static final JSONNull NULL = new JSONNull();

	@Override
	protected final Object clone() {
		return this;
	}

	@Override
	public boolean equals(Object object) {
		return object == null || object == this;
	}

	public String toString() {
		return "null";
	}
}