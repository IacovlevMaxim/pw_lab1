package assignment1;

import java.time.LocalDate;

public class Player {
	private String full_name;
	private LocalDate birth;
	private LocalDate registration;
	private String email;
	
	// METHODS
	
	public Player() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param full_name Full_name of the user
	 * @param birth
	 * @param registration
	 * @param email
	 */
	public Player(String full_name, LocalDate birth, LocalDate registration, String email) {
		this.full_name = full_name;
		this.birth = birth;
		this.registration = registration;
		this.email = email;
	}
	
	public String getName() {
		return this.full_name;
	}
	
	public LocalDate getBirth() {
		return this.birth;
	}
	
	public LocalDate getRegistration() {
		return this.registration;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setName(String name) {
		this.full_name = name;
	}
	
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	
	public void setRegistration(LocalDate registration) {
		this.registration = registration;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

	public String toString() { // DO WE NEED TO SEE IF THE VARIABLES ARE EMPTY TO PRINT THEM
		String playerInfo;
		playerInfo = "\nPlayer's name: " + this.full_name;
		playerInfo += "\nDate of birth: " + this.birth;
		playerInfo += "\nFirst registration: " + this.registration;
		playerInfo += "\nEmail addres: " + this.email;
		
		return playerInfo;
	}
	
	public int calculateSeniority() { // IT DOESN'T WORK
		int seniority;
		LocalDate ahora= LocalDate.now();
		this.registration.getYear();
		seniority = ahora.getYear() - this.registration.getYear();
		return seniority;
	}
	
	
	public static void main(String[] args) {
		// HOW TO SET A DATE, ALL METHODS ARE DEPRECATED, DO WE USE CALENDAR??
		Player jugador = new Player("Antonio", LocalDate.of(2004, 11, 20), LocalDate.of(2021, 2, 28), "asdocvan@ivas.com");
		String info = jugador.toString();
		int seniority = jugador.calculateSeniority();

		System.out.println(info + "\n"); // IF YOU WRITE "\t" IT SHOWS AS A TAB
		System.out.println(seniority);
	}
}
