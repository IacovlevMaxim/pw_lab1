package display;

import java.util.Scanner;

import business.*;
import business.enums.*;

public class CourtManagerMain {

	public static void main(String[] args) {

		String opt="1";
		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		// PARAMETERS FOR THE COURT //
		String cName, aux;
		CourtSize cSize; 
		MaterialType mType;
		MaterialStatus mStatus;
		boolean cStatus, cType, mUsage;
		int mId, maxNum=0;
		//////////////////////////////
		CourtManager cManager = new CourtManager(); // COURT MANAGER
		
		while(!opt.equals("0"))
		{
			
			System.out.println("\n-------------------- MENU --------------------\n");			
			System.out.println("0) Go back\n1) Create new court\n2) Create new material\n3) Associate material to court\n4) List courts\n5) List materials\n6) List unavailable courts\n7) List fitting courts\n");
			System.out.println("----------------------------------------------\n ");	
			opt = sc.next();
			
			switch (opt) {
			
				case "1":
					System.out.println("\nInsert new court's information...");
					System.out.println("\nName (WARNING: It must be unique): ");
					cName=sc.next();
					cName+=sc.nextLine();
					System.out.println("\nStatus (0-Available, 1-Unavailable): ");
					aux = sc.next();
					if(aux.equals("0")){cStatus=Boolean.parseBoolean("True");}
					else {cStatus=Boolean.parseBoolean("False");}
					System.out.println("\nType (0-Indoors, 1-Outdoors): ");
					aux = sc.next();
					if(aux.equals("0")){cType=Boolean.parseBoolean("True");}
					else {cType=Boolean.parseBoolean("False");}
					System.out.println("\nCourt size (MINIBASKET, ADULTS, THREE_VS_THREE): ");
					aux = sc.next();
					try {
						cSize = CourtSize.valueOf(aux.toUpperCase());
						System.out.println("\nMaximum number of players: ");
						maxNum = Integer.parseInt(sc.next());
						// cManager
						System.out.println("\nCourt created successfully!\n");
					}
					catch(NumberFormatException e)
					{
						System.out.println("\nImpossible to process. Non numeric number of players\n");
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("\nInvalid Court Size. Unable to create court.\n");
					}
					break;
					
				case "2":
					try {
						System.out.println("\nInsert new material's information...");
						System.out.println("\nType (BALL, BASKET, CONE): ");
						aux = sc.next();
						mType = MaterialType.valueOf(aux.toUpperCase());
						System.out.println("\nStatus (AVAILABLE, RESERVED, BAD_CONDITION): ");
						aux = sc.next();
						mStatus = MaterialStatus.valueOf(aux.toUpperCase());
						System.out.println("\nUsage (0-Indoors, 1-Outdoors): ");
						aux = sc.next();
						if(aux.equals("0")){mUsage=Boolean.parseBoolean("True");}
						else {mUsage=Boolean.parseBoolean("False");}
						// cManager
					}
					catch(IllegalArgumentException e) {
						System.out.println("\nInvalid ENUM. Unable to create material.\n");
					}
					break;
					
				case "3":
					try {
						System.out.println("\nInsert ID of the material to associate: ");
						aux = sc.next();
						mId = Integer.parseInt(aux);
						System.out.println("\nInsert the name of the desired court: ");
						cName=sc.next();
						cName+=sc.nextLine();
						cName=cName.replaceAll(" ", "_");
						// cManager
					}	
					/*catch(ImpossibleToReserveException e)
					{
						e.printStackTrace();
					}*/
					catch(NumberFormatException e)
					{
						System.out.println("\nInvalid ID\n");
					}
					break;
					
				case "4":
					System.out.println("\n--- COURTS ---\n");
					/*for(int i=0; i<cManager.getCourts().size(); i++) {
						System.out.println(cManager.getCourts().get(i).toString() + "\n");
					}*/
					break;
					
				case "5":
					System.out.println("\n--- MATERIALS ---\n");
					/*for(int i=0; i<cManager.getMaterials().size(); i++) {
						System.out.println(cManager.getMaterials().get(i).toString() + "\n");
					}*/
					break;
					
				case "6":
					System.out.println("\nUNAVAILABLE COURTS\n");
					/*for(int i=0; i<cManager.listUnavailableCourts().size(); i++) {
						System.out.println(cManager.listUnavailableCourts().get(i).toString() + "\n");
					}*/
					break;
					
				case "7":
					try {
						System.out.println("\nInsert the number of players you desire to accommodate: ");
						aux = sc.next();
						maxNum = Integer.parseInt(aux);
						System.out.println("\nInsert the type of court (0-Indoors, 1-Outdoors): ");
						aux=sc.next();
						if(aux.equals("0")){cType = true;}
						else {cType=false;}
						System.out.println("\n--- FITTING COURTS ---\n");
						/*courts = cManager.listFittingCourts(maxNum, cType);
						for(int i=0; i<courts.size(); i++) {
							System.out.println(courts.get(i).toString() + "\n");
						}*/
					}
					catch(NumberFormatException e)
					{
						System.out.println("\nImpossible to process. Non numeric number of players\n");
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
