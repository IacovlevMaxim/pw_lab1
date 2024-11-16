package business.exceptions;

public class PlayerAlreadyExistsException extends Exception {
	
	/**
	 * Empty constructor
	 */
	public PlayerAlreadyExistsException() {

    }

	/**
	 * Sends a message
	 * @param error The message to send
	 */
    public PlayerAlreadyExistsException(String error) {
    	super(error);
    }
}
