package business;

public class UserDTO {

	private String email = "";
	private String password = "";
	private Boolean type = false;

	/**
	 * Empty constructor
	 */
	public UserDTO() {
		
	}

	/**
	 * Parameterized constructor
	 * @param email Email of the user
	 * @param password Password of the user
	 * @param type Type of user (false: Client, true: Administrator)
	 */
	public UserDTO(String email, String password, Boolean type) {
		this.email = email;
		this.password=password;
		this.type=type;
	}
	
	/**
	 * Gets the email of the user
	 * @return The email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets a new email for the user
	 * @param email The new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the password of the user
	 * @return The password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets a password for the user
	 * @param pw The new password
	 */
	public void setPassword(String pw) {
		this.password = pw;
	}
	
	/**
	 * Type of user (false: Client, true: Administrator)
	 * @return false if it's a normal client, true if it's an administrator
	 */
	public Boolean getType() {
		return type;
	}
	
	/**
	 * Sets the type of user
	 * @param type The new type of user (false if it's a normal client, true if it's an administrator)
	 */
	public void setType(Boolean type) {
		this.type=type;
	}
}
