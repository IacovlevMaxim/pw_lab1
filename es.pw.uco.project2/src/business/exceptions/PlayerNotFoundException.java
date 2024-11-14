package business.exceptions;

public class PlayerNotFoundException extends Exception {
	
	/**
	 * Empty constructor
	 */
	public PlayerNotFoundException() {

    }

	/**
	 * Sends a message
	 * @param error The message to send
	 */
    public PlayerNotFoundException(String error) {
    	super(error);
    }
}
