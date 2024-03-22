package application.britishTime.exception;

public class InvalidTimeFormatException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTimeFormatException(String message) {
        super(message);
    }
}