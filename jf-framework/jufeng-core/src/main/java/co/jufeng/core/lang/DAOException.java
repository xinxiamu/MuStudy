package co.jufeng.core.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DAOException extends RuntimeException{

	private static final long serialVersionUID = -1528837359960065978L;

	
	public DAOException() {
		super();
		
	}

	public DAOException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DAOException(String message) {
		super(message);
		
	}

	public DAOException(Throwable cause) {
		super(cause);
		
	}

	@Override
	public String getMessage() {
		
		return super.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		
		return super.getLocalizedMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		
		return super.initCause(cause);
	}

	@Override
	public String toString() {
		
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		
		super.printStackTrace(s);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		
		return super.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		
		super.setStackTrace(stackTrace);
	}
	
	

}
