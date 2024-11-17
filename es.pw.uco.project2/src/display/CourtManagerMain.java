package display;

import java.util.Scanner;

import business.*;
import business.enums.*;
import business.exceptions.CourtAlreadyExistsException;
import business.exceptions.ImpossibleToReserveException;
import business.exceptions.MaterialNotFoundException;

public class CourtManagerMain {

	public static void main(String[] args) {

		String opt="1";
		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		// PARAMETERS FOR THE COURT AND MATERIALS //
		String cName, aux;
		CourtSize cSize; 
		MaterialType mType;
		MaterialStatus mStatus;
		boolean cStatus, cType, mUsage;
		int mId, maxNum=0;
		////////////////////////////////////////////
		CourtManager cManager = new CourtManager(); // COURT MANAGER
		
		while(!opt.equals("0"))
		{
			
			System.out.println("\n-------------------- MENU --------------------\n");			
			System.out.println("0) Go back\n1) Create new court\n2) Create new material\n3) Associate material to court\n4) List courts\n5) List materials\n6) List unavailable courts\n7) List fitting courts\n8) List materials associated to court\n");
			System.out.println("----------------------------------------------\n ");	
			opt = sc.next();
			
			switch (opt) {
			
				case "1":
					System.out.println("\nInsert new court's information (The new court will be available by default)...");
					System.out.println("\nName (WARNING: It must be unique): ");
					cName=sc.next();
					cName+=sc.nextLine();
					cStatus=true;
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
						cManager.createCourt(cName, cStatus, cType, cSize, maxNum);
						System.out.println("\nCourt created successfully!\n");
					}
					catch(NumberFormatException e)
					{
						System.out.println("\nImpossible to process. Non numeric number of players\n");
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("\nInvalid Court Size. Unable to create court.\n");
					} catch (CourtAlreadyExistsException e) {
						e.printStackTrace();
					}
					break;
					
				case "2":
					try {
						System.out.println("\nInsert new material's information (The new material will be available by default)...");
						System.out.println("\nType (BALL, BASKET, CONE): ");
						aux = sc.next();
						mType = MaterialType.valueOf(aux.toUpperCase());
						mStatus = MaterialStatus.AVAILABLE;
						System.out.println("\nUsage (0-Indoors, 1-Outdoors): ");
						aux = sc.next();
						if(aux.equals("0")){mUsage=Boolean.parseBoolean("True");}
						else {mUsage=Boolean.parseBoolean("False");}
						cManager.createMaterial(mType, mUsage, mStatus);
						System.out.println("\nMaterial created successfully!\n");
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
						cManager.associateMaterial(cName, mId);
						System.out.println("\nMaterial associated successfully!\n");
					} catch(NumberFormatException e) {
						System.out.println("\nInvalid ID. Unable to reserve material.\n");
					} catch (ImpossibleToReserveException e) {
						e.printStackTrace();
					} catch (MaterialNotFoundException e) {
						e.printStackTrace();
					}
					break;
					
				case "4":
					System.out.println("\n--- COURTS ---");
					for(String s : cManager.getCourtsString(cManager.getCourts()))
					{
						System.out.println(s);
					}
					break;
					
				case "5":
					System.out.println("\n--- MATERIALS ---");
					for(String s : cManager.getMaterialsString(cManager.getMaterials()))
					{
						System.out.println(s);
					}
					break;
					
				case "6":
					System.out.println("\n--- UNAVAILABLE COURTS ---");
					for(String s : cManager.getCourtsString(cManager.listUnavailableCourts()))
					{
						System.out.println(s);
					}
					break;
					
				case "7":
					try {
						System.out.println("\nInsert the number of players you desire to accommodate: ");
						aux = sc.next();
						maxNum = Integer.parseInt(aux);
						System.out.println("\nInsert the size of the court (MINIBASKET, ADULTS, THREE_VS_THREE): ");
						aux = sc.next();
						cSize = CourtSize.valueOf(aux.toUpperCase());
						System.out.println("\n--- FITTING COURTS ---");
						for(String s : cManager.getCourtsString(cManager.listFittingCourts(maxNum, cSize)))
						{
							System.out.println(s);
						}
					}
					catch(NumberFormatException e)
					{
						System.out.println("\nImpossible to process. Non numeric number of players\n");
					}
					
					break;
					
				case "8":
					System.out.println("\nInsert name of the court: ");
					cName=sc.next();
					cName+=sc.nextLine();
					System.out.println("\n--- MATERIALS  ---");
					for(String s : cManager.getMaterialsString(cManager.getMaterialsAssociatedToCourt(cName)))
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

	}

}
