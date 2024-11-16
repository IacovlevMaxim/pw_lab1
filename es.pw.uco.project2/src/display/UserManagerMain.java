package display;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import business.UserManager;
import business.exceptions.*;

public class UserManagerMain {

	public static void main(String[] args) {

		String opt="1"; // OPTION FOR THE SWITCH
		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		// PARAMETERS FOR THE PLAYER //
		String name, email, newEmail;
		LocalDate birth, registration;
		///////////////////////////////
		UserManager uManager = new UserManager(); // USER MANAGER
		
		while(!opt.equals("0"))
		{
			
			System.out.println("\n-------------------- MENU --------------------\n");			
			System.out.println("0) Go back\n1) Register an user\n2) Modify an user's information\n3) List existing users\n");
			System.out.println("----------------------------------------------\n ");	
			opt = sc.next();
			
			switch (opt) {
				case "1":
					System.out.println("\nInsert new user's information...");
					System.out.println("\nName >> ");
					name=sc.next();
					name+=sc.nextLine();
					System.out.println("\nEmail (WARNING: It must be unique): ");
					email = sc.next();
					registration = LocalDate.now();
					System.out.println("\nBirthday (FORMAT: YYYY-MM-DD):");
					try {
						birth = LocalDate.parse(sc.next());
						uManager.register(name, birth, registration, email);
						System.out.println("\nUser successfully registered!\n");
					}
					catch(DateTimeParseException e) {
						System.out.println("\nInvalid date format. Unable to create the new player.\n");
					}	
					catch(PlayerAlreadyExistsException e) {
						e.printStackTrace();
					}
					break;
					
				case "2":
					System.out.println("\nWhich user do you want to modify? (Use the email): ");
					email = sc.next();
					System.out.println("\nInsert user's new information...");
					System.out.println("\nName >> ");
					name=sc.next();
					name+=sc.nextLine();
					System.out.println("\nEmail (WARNING: It must be unique): ");
					newEmail = sc.next();
					System.out.println("\nBirthday (FORMAT: YYYY-MM-DD): ");
					try {
						birth = LocalDate.parse(sc.next());
						uManager.modifyUser(email, name, birth, newEmail);
						System.out.println("\nUser succesifully modified!");
					}
					catch(DateTimeParseException e) {
						System.out.println("\nInvalid date format. Unable to create the new player\n");
					}
					catch(PlayerAlreadyExistsException e) {
						e.printStackTrace();
					}
					catch(PlayerNotFoundException e) {
						e.printStackTrace();
					}
					break;
					
				case "3":
					System.out.println("\n--- USERS ---");
					for(String s : uManager.getPlayersString())
					{
						System.out.println(s);
					}
					break;
	
				case "0":
					break;
					
				default:
					System.out.println("\n~ Opcion no valida ~\n\n");	
					break;
			}
		}
		sc.close();
	}

}
