package co.jufeng.logger;

public class TraceInfoUtil {
	
	public static String getThisClassRunPath() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("class: ").append(stacks[1].getClassName());
		return sb.toString();
	}
	
	public static String getThisClassRunMethodName() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
				sb.append("; method: ").append(stacks[1].getMethodName());
		return sb.toString();
	}
	
	public static String getThisClassRunLine() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
				sb.append("; line: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}
	
	public static String getThisClassRunMethodNameLine() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
				sb.append("; method: ").append(stacks[1].getMethodName())
				.append("; line: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}
	
	public static String getTraceInfo(){   
        StringBuffer sb = new StringBuffer();    
        StackTraceElement[] stacks = new Throwable().getStackTrace();   
        sb.append("class: " ).append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; line: ").append(stacks[1].getLineNumber());   
        return sb.toString();   
	}   
	
	public static StringBuffer getExceptionTraceInfo(Class<?> clazz, Throwable e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = e.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getClassName().contains(clazz.getName())) {
				sb.append("class: ").append(stacks[i].getClassName())
						.append("; method: ").append(stacks[i].getMethodName())
						.append("; line: ").append(stacks[i].getLineNumber())
						.append(";  Exception: ").append(getExceptionMessage(e));
				break;
			}
		}
		return sb;
	}

	public static int getExceptionLine(Class<?> clazz, Throwable e) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = e.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getClassName().contains(clazz.getName())) {
						sb.append(stacks[i].getLineNumber());
				break;
			}
		}
		if(sb.length() == 0){
			return -1;
		}else{
			return Integer.valueOf(sb.toString());
		}
		
	}
	
	public static String getExceptionMessage(Throwable e) {
		String message = e.toString();
		if (message.lastIndexOf(":") != -1){
			message = message.substring(0, message.lastIndexOf(":"));
		}
		return message;
	}
	
}