package co.jufeng.string;

import java.io.PrintStream;
import java.io.PrintWriter;

@SuppressWarnings({"rawtypes"})
public class NestableRuntimeException extends RuntimeException implements Nestable {
    
    private static final long serialVersionUID = 1L;

    protected NestableDelegate delegate = new NestableDelegate(this);

    private Throwable cause = null;

    public NestableRuntimeException() {
        super();
    }

    public NestableRuntimeException(String msg) {
        super(msg);
    }

    public NestableRuntimeException(Throwable cause) {
        super();
        this.cause = cause;
    }

    public NestableRuntimeException(String msg, Throwable cause) {
        super(msg);
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    public String getMessage() {
        if (super.getMessage() != null) {
            return super.getMessage();
        } else if (cause != null) {
            return cause.toString();
        } else {
            return null;
        }
    }

    public String getMessage(int index) {
        if (index == 0) {
            return super.getMessage();
        }
        return delegate.getMessage(index);
    }

    public String[] getMessages() {
        return delegate.getMessages();
    }

    public Throwable getThrowable(int index) {
        return delegate.getThrowable(index);
    }

    public int getThrowableCount() {
        return delegate.getThrowableCount();
    }

    public Throwable[] getThrowables() {
        return delegate.getThrowables();
    }

    public int indexOfThrowable(Class type) {
        return delegate.indexOfThrowable(type, 0);
    }

    public int indexOfThrowable(Class type, int fromIndex) {
        return delegate.indexOfThrowable(type, fromIndex);
    }

    public void printStackTrace() {
        delegate.printStackTrace();
    }

    public void printStackTrace(PrintStream out) {
        delegate.printStackTrace(out);
    }

    public void printStackTrace(PrintWriter out) {
        delegate.printStackTrace(out);
    }

    public final void printPartialStackTrace(PrintWriter out) {
        super.printStackTrace(out);
    }

}
