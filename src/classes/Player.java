package assignment1;

import java.text.DateFormat;
import java.util.Date;

public class Player {
	private String full_name;
	private Date birth;
	private Date registration;
	private String email;
	
	// METHODS
	
	public Player() {
		
	}
	
	public Player(String full_name, Date birth, Date registration, String email) {
		this.full_name = full_name;
		this.birth = birth;
		this.registration = registration;
		this.email = email;
	}
	
	public String getName() {
		return this.full_name;
	}
	
	public Date getBirth() {
		return this.birth;
	}
	
	public Date getRegistration() {
		return this.registration;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setName(String name) {
		this.full_name = name;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public void setRegistration(Date registration) {
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
		long aux;
		int seniority;
		Date now = new Date();
		aux = now.getTime() - this.registration.getTime();
		aux = aux/1000000; // MILISEGUNDOS A SEGUNDOS
		aux = aux/86400; // SEGUNDOS EN UN DIA
		seniority = (int)aux/365; // DIAS EN UN AÑO
		return seniority;
	}
	
	
	
	public static void main(String[] args) {
		// HOW TO SET A DATE, ALL METHODS ARE DEPRECATED, DO WE USE CALENDAR??
		Player jugador = new Player("Antonio", new Date(104, 12, 10), new Date(121, 10, 23), "asdocvan@ivas.com");
		String info = jugador.toString();
		int seniority = jugador.calculateSeniority();

		System.out.println(info + "\n"); // IF YOU WRITE "\t" IT SHOWS AS A TAB
		System.out.println(seniority);
	}
}
