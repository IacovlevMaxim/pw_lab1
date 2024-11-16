package business.exceptions;

public class MaterialNotFoundException extends Exception {
	
	/**
	 * Empty constructor
	 */
	public MaterialNotFoundException() {

    }

	/**
	 * Sends a message
	 * @param error The message to send
	 */
    public MaterialNotFoundException(String error) {
    	super(error);
    }
}
