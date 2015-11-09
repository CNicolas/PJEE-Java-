package pjee.exception;

public class PjeeFileException extends Exception {
	private static final long serialVersionUID = 1L;

	public PjeeFileException(String message) {
		super(message);
	}

	public PjeeFileException(Throwable thr) {
		super(thr);
	}

	public PjeeFileException(String message, Throwable thr) {
		super(message, thr);
	}
}
