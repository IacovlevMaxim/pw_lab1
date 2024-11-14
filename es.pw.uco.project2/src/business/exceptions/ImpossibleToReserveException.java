package business.exceptions;

/**
 * Exception for when a court or material cannot be reserved
 */
public class ImpossibleToReserveException extends Exception {
	/**
	 * Empty constructor
	 */
	public ImpossibleToReserveException() {

    }

	/**
	 * Sends a message
	 * @param error The message to send
	 */
    public ImpossibleToReserveException(String error) {
    	super(error);
    }
}
