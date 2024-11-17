package business.exceptions;

public class CourtAlreadyExistsException extends Exception {
	/**
	 * Empty constructor
	 */
	public CourtAlreadyExistsException() {}
	/**
	 * Sends a message
	 * @param error The message to send
	 */
	public CourtAlreadyExistsException(String err)
	{
		
		super(err);
		
	}
}
