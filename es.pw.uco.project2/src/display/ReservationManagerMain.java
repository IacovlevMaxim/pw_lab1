package display;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationManagerMain {

	public static void main(String[] args) {
		
		String opt="1"; // OPTION FOR THE SWITCH
		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		// PARAMETERS FOR THE RESERVATION AND COURTS //
		String email, aux, cName;
		int duration=0, maxAdults=0, maxChildren=0, num=0;
		LocalDate reservation;
		boolean cType;
		///////////////////////////////////////////////
		
		opt="1";
		while(!opt.equals("0")) // THE MENU FOR THE USER MANAGER STARTS HERE
		{
			System.out.println("\n-------------------- MENU --------------------\n");			
			System.out.println("0) Go back\n1) Make an individual reservation\n2) Make reservations within a package (Doesn't work)\n3) Cancel a reservation\n4) Modify a reservation\n5) Consult number of future reservations\n6) Consult future reservations\n");
			System.out.println("----------------------------------------------\n\n> ");	
			opt = sc.next();
			
			switch (opt) {
				
				case "1":
					System.out.println("\nEmail of the user making the reservation >> ");
					email = sc.next();
					System.out.println("\nWhat type of reservation will it be? \n0): Children\n1) Adult\n2) Family\n");
					aux = sc.next();
					if(!aux.equals("0") && !aux.equals("1") && !aux.equals("2")) {System.out.println("\nInvalid type of reservation\n");}
					else
					{	
						System.out.println("\nDuration of the reservation >> ");
						try {
							duration = Integer.parseInt(sc.next());
							System.out.println("\nName of the court to reserve >> ");
							cName = sc.next();
							System.out.println("\nDate of the reservation >> ");
							reservation = LocalDate.parse(sc.next());
							System.out.println("\nInsert the type of court (0-Indoors, 1-Outdoors) >> ");
							aux=sc.next();
							if(aux.equals("0")){cType = true;}
							else {cType=false;}
							maxAdults=0;
							maxChildren=0;
							switch(aux) {
							case "0":
								System.out.println("\nHow many children do you want to fit? >> ");
								maxChildren = Integer.parseInt(sc.next());
								break;
							case "1":
								System.out.println("\nHow many adults do you want to fit? >> ");
								maxAdults = Integer.parseInt(sc.next());
								break;
							case "2":
								System.out.println("\nHow many adults do you want to fit? >> ");
								maxAdults = Integer.parseInt(sc.next());
								System.out.println("\nHow many children do you want to fit? >> ");
								maxChildren = Integer.parseInt(sc.next());
								break;
							}

							//if(rManager.makeIndividualReservation(Integer.parseInt(aux), email, reservation, duration, name, maxNum, maxNum2, cType)==true) {System.out.println("\nCourt " + name + " reserved succesfully\n");}
						} 
						catch(NumberFormatException e)
						{
							System.out.println("\nImpossible to process -Invalid duration of the reservation\n");
						}
						catch(DateTimeParseException e) {
							System.out.println("\nInvalid date format - Unable to create the reservation\n");
						}
						/*catch(NoAdultException e) {
							e.printStackTrace();
						}*/
					}
					break;
				case "2":
						
					break;
				case "3":
					System.out.println("\nEmail of the user that made the reservation >> ");
					email = sc.next();
					System.out.println("\nName of the reserved court >> ");
					cName = sc.next();
					//if(rManager.cancelReservation(email, name)==true) {System.out.println("\nReservation cancelled successfully\n");}
					break;
					
				case "4":
					System.out.println("\nEmail of the user that made the reservation >> ");
					email = sc.next();
					System.out.println("\nName of the reserved court >> ");
					cName = sc.next();
					try {
						System.out.println("\nNew date of the reservation >> ");
						reservation = LocalDate.parse(sc.next());
						System.out.println("\nNew duration of the reservation >> ");
						duration = Integer.parseInt(sc.next());
						//if(rManager.modifyReservation(email, name, reservation, duration)==true) {System.out.println("\nReservation successfully modified >> ");}
					}
					catch(DateTimeParseException e) {
						System.out.println("\nInvalid date format - Unable to modify the reservation\n");
					}	
					break;
					
				case "5":
					//num=rManager.numberOfReservations();
					System.out.println("\nThere are currently " + num + " reservations\n");
					break;

				case "6":
					/*reservations = rManager.listReservations();
					if(reservations.size()==0) {
						System.out.println("\nNo ongoing reservations\n");
					}
					else {
						for(Reservation r : reservations) {
							System.out.println(r.toString());
						}
					}*/
					break;
				case "7":
					System.out.println("\nName of the court to consult >> ");
					cName = sc.next();
					try {
						System.out.println("\nDate of the reservation to consult >> ");
						reservation = LocalDate.parse(sc.next());
						/*reservations = rManager.getReservations(reservation, name);
						if(reservations.size()==0) {
							System.out.println("\nNo reservations for that date and court\n");
						}
						else
						for(Reservation r : reservations) {
							
						}*/
					}
					catch(DateTimeParseException e) {
						System.out.println("\nInvalid date format - Unable to modify the reservation\n");
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
