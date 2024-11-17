package business;

import java.time.LocalDate;

/**
 * Data transfer object for the packages
 */
public class PackageDTO {

	private Integer id;
	private int userId;
	private LocalDate startDate;
	private LocalDate expiration;
	
	/**
	 * Empty constructor
	 */
	public PackageDTO() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param id Id of the reservation
	 * @param userId User who made the reservation
	 * @param startDate Start date of the reservation
	 */
	public PackageDTO(int id, int userId, LocalDate startDate) {
		this.id = id;
		this.userId = userId;
		this.startDate = startDate;
		this.expiration = startDate.plusYears(1);
	}

	/**
	 * Parameterized constructor
	 * @param userId User who made the reservation
	 * @param startDate Start date of the reservation
	 */
	public PackageDTO(int userId, LocalDate startDate) {
		this.id = null;
		this.userId = userId;
		this.startDate = startDate;
		this.expiration = startDate.plusYears(1);
	}
	
	/**
	 * Getter for the id of the package
	 * @return The id of the package
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the package
	 * @param id The new id of the package
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the user id of the package
	 * @return The user id of the package
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id of the package
	 * @param userId The new user id of the package
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Getter for the start date of the package
	 * @return The start date of the package
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date of the package
	 * @param startDate New start date of the package
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Getter for the expiration date of the package
	 * @return The expiration date of the package
	 */
	public LocalDate getExpiration() {
		return expiration;
	}

	/**
	 * Sets the expiration date  of the package
	 * @param expiration New expiration date of the package
	 */
	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	/**
	 * To string method
	 */
	public String toString() {
		return "\nPackage's ID: " + id + "\nID of the user who made the reservation: " + userId + "\nStart date: " + startDate + "\nExpiration date: "
				+ expiration;
	}



}
