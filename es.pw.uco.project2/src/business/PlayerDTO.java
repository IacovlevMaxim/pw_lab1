package business;

import java.time.LocalDate;

/**
 * Data object of the player
 */
public class PlayerDTO {

	private String fullName;
	private LocalDate birth;
	private LocalDate registration;
	private String email;
	
	/**
	 * Empty constructor
	 */
	public PlayerDTO() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param fullName Full_name of the user
	 * @param birth
	 * @param reservation
	 * @param email
	 */
	public PlayerDTO(String fullName, LocalDate birth, LocalDate registration, String email) {
		this.fullName = fullName;
		this.birth = birth;
		this.registration = registration;
		this.email = email;
	}
	
	/**
	 * Gets the name of the player
	 * @return The name of the player
	 */
	public String getName() {
		return this.fullName;
	}
	/**
	 * Gets the birthday of the player
	 * @return The birthday of the player
	 */
	public LocalDate getBirth() {
		return this.birth;
	}
	/**
	 * Gets the registration date of the player
	 * @return The registration day of the player
	 */
	public LocalDate getRegistration() {
		return this.registration;
	}
	/**
	 * Gets the email of the player, which must be unique
	 * @return The email of the player
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * Sets the name of the player
	 * @param name New name of the player
	 */
	public void setName(String name) {
		this.fullName = name;
	}
	/**
	 * Sets the birthday of the player
	 * @param birth New birthday of the player
	 */
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	/**
	 * Sets the registration date of the player
	 * @param registration New registration day of the player
	 */
	public void setRegistration(LocalDate registration) {
		this.registration = registration;
	}
	/**
	 * Sets the email of the player
	 * @param email New email of the player
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Creates a string to print the information of the player
	 * @return The string containing the information of the player
	 */
	public String toString() { // DO WE NEED TO SEE IF THE VARIABLES ARE EMPTY TO PRINT THEM
		String playerInfo;
		String name = this.fullName.replaceAll("_", " ");
		playerInfo = "\nPlayer's name: " + name;
		playerInfo += "\nDate of birth: " + this.birth;
		playerInfo += "\nFirst reservation: " + this.registration;
		playerInfo += "\nEmail addres: " + this.email;
		
		return playerInfo;
	}
	
	/**
	 * Computes the number of years the user has been registered
	 * @return The number of years
	 */
	public int calculateSeniority() {
		int seniority;
		LocalDate ahora= LocalDate.now();
		this.registration.getYear();
		seniority = ahora.getYear() - this.registration.getYear();
		return seniority;
	}
}
