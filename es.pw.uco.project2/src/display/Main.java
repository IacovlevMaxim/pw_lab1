package display;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		String opt="1"; // OPTION FOR THE MAIN PROGRAM
		
		while(!opt.equals("0")) {
			
			System.out.println("\nWelcome to Basketball's! How would you like to log in?\n0) Exit program\n1) User Manager\n2) Court Manager\n3) Reservation Manager\n ");
			opt = sc.next();		
			
			switch(opt) {
			
				case "1":
					UserManagerMain.main(args);
					break;
				
				case "2":
					CourtManagerMain.main(args);
					break;
					
				case "3":
					ReservationManagerMain.main(args);
					break;

				case "0":
					System.out.println("\nSaving changes and ending program...\n");	
					break;
				
				default:
					System.out.println("\n~ Opcion no valida ~\n");			
					break;
			}
		}
		sc.close();
	}
}
