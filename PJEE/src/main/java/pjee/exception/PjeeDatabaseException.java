package pjee.exception;

public class PjeeDatabaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public PjeeDatabaseException(String message) {
		super(message);
	}

	public PjeeDatabaseException(Throwable thr) {
		super(thr);
	}

	public PjeeDatabaseException(String message, Throwable thr) {
		super(message, thr);
	}

}
