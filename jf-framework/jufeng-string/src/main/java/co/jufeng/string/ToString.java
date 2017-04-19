package co.jufeng.string;

public class ToString {
	
	@Override
	public String toString() {
		return co.jufeng.string.ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE_MAP);
	}

}
