package co.jufeng.barcode.encode;

public class InvalidAtributeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAtributeException() {
		super();
	}

	public InvalidAtributeException(String message) {
		super(message);
	}

	public InvalidAtributeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidAtributeException(Throwable cause) {
		super(cause);
	}

}
