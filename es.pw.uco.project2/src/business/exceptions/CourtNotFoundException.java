package business.exceptions;

public class CourtNotFoundException extends Exception{
	
	/**
	 * Empty constructor
	 */
	public CourtNotFoundException() {}
	/**
	 * Sends a message
	 * @param error The message to send
	 */
	public CourtNotFoundException(String err)
	{
		
		super(err);
		
	}
}
