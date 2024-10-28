import classes.*;
import enums.CourtSize;
import enums.MaterialStatus;
import enums.MaterialType;
import manager.*;
import exceptions.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Properties;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		String optMain="1"; // STRING TO MANAGE THE MANAGERS
		String optFunction="1"; // STRING TO MANAGE THE OPTIONS PROVIDED TO THE USER
		String line; // STRING FOR READING THE LINES OF THE FILES
		String datos[]; // READER FOR EACH ELEMENT OF THE LINE OF A FILE
		String aux; // AUXILIAR VARIABLE
		Scanner sc = new Scanner(System.in); // SCANNER TO READ THE KEYBOARD
		ArrayList<Player> players = new ArrayList<Player>(); // AUXILIAR PLAYERS LIST
		ArrayList<Material> materials =  new ArrayList<Material>(); // AUXILIAR MATERIALS LIST
		ArrayList<Court> courts =  new ArrayList<Court>(); // AUXILIAR COURTS LIST
		UserManager uManager = new UserManager(); // USER MANAGER
		CourtManager cManager = new CourtManager(); // COURT MANAGER
		
		// LOADING THE FILES...
		
		ConfigLoader loader = new ConfigLoader();
		
		String name, email;
		LocalDate birth, registration;
		boolean cStatus, cType, mUsage;
		CourtSize cSize;
	    MaterialType mType;
	    MaterialStatus mStatus;
		int maxNum, id;
		Player player = new Player();
	    Material material = new Material();
	    Court court = new Court();
		
		try { // GETTING THE USERS INFORMATION

			//System.out.println("Paths: " + loader.getProperty("CourtsFile"));		
			/*BufferedReader usersReader = new BufferedReader(new FileReader(new File("src/data/users.txt")));
			BufferedReader courtReader = new BufferedReader(new FileReader(new File("src/data/courts.txt")));
			BufferedReader materialsReader = new BufferedReader(new FileReader(new File("src/data/materials.txt")));	*/
			BufferedReader usersReader = new BufferedReader(new FileReader(loader.getProperty("PlayersFile")));
			BufferedReader courtReader = new BufferedReader(new FileReader(loader.getProperty("CourtsFile")));
			BufferedReader materialsReader = new BufferedReader(new FileReader(loader.getProperty("MaterialsFile")));
			while((line = usersReader.readLine()) != null) {
				datos=line.split(" ");
				name = datos[0]; 
				birth = LocalDate.parse(datos[1]);
				registration = LocalDate.parse(datos[2]);
				email = datos[3];
				player = new Player(name, birth, registration, email);
				uManager.register(player);	
			}
			while((line = materialsReader.readLine()) != null) { // PROCESSING THE MATERIALS FILE
				datos=line.split(" ");
				mType=MaterialType.valueOf(datos[0]);
				mUsage=Boolean.parseBoolean(datos[1]);
				mStatus=MaterialStatus.valueOf(datos[2]);
				cManager.createMaterial(mType, mUsage, mStatus); // THE ID IS WRITTEN AUTOMATICALLY
			}
			
			while((line = courtReader.readLine()) != null) { // PROCESSING THE COURTS FILE
				datos=line.split(" ");
				name = datos[0];
				cStatus = Boolean.parseBoolean(datos[1]);
				cType = Boolean.parseBoolean(datos[2]);
				cSize = CourtSize.valueOf(datos[3]);
				maxNum = Integer.parseInt(datos[4]);
				ArrayList<Integer> ids = new ArrayList<Integer>();
				for(int i=5; i<datos.length; i++) {
					ids.add(Integer.parseInt(datos[i]));
				}
				cManager.createCourt(name, cStatus, cType, cSize, maxNum, ids);
			}
			usersReader.close();
			courtReader.close();
			materialsReader.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		// END OF FILE LOADING...
		
		while(!optMain.equals("0")) {
			
			System.out.println("\nWelcome to Basketball's! How would you like to log in?\n0) Exit program\n1) User Manager\n2) Court Manager\n3) Reservation Manager\n ");
			optMain = sc.next();

			//System.out.println("\n" + optMain);	
//___________________________________________________________________________________________________________________			
			
			switch(optMain) {
			
				case "1":
					optFunction="1";
					while(!optFunction.equals("0")) // THE MENU FOR THE USER MANAGER STARTS HERE
					{
						
						System.out.println("\n-------------------- MENU --------------------\n");			
						System.out.println("0) Go back\n1) Register an user\n2) Modify an user's information\n3) List existing users\n");
						System.out.println("----------------------------------------------\n ");	
						optFunction = sc.next();
						
						switch (optFunction) {
							case "1":
								System.out.println("\nInsert new user's information...");
								System.out.println("\nName >> ");
								name=sc.next();
								name+=sc.nextLine();
								name=name.replaceAll(" ", "_");
								System.out.println("\nEmail (WARNING: It must be unique): ");
								email = sc.next();
								System.out.println("\nBirthday (FORMAT: YYYY-MM-DD):");
								registration = LocalDate.now();
								
								try {
									birth = LocalDate.parse(sc.next());
									if(uManager.register(new Player(name, birth, registration, email))==true){ System.out.println("\nUser successfully registered.\n");}
									else{ System.out.println("\nAlready existing email - Could not register the user.\n");}
								}
								catch(DateTimeParseException e) {
									System.out.println("\nInvalid date format - Unable to create the new player\n");
								}	
								break;
								
							case "2":
								System.out.println("\nWhich user do you want to modify? (Use the email): ");
								email = sc.next();
								if((player=uManager.isRegistered(email))!=null)
								{
									System.out.println("\nInsert user's new information...");
									System.out.println("\nName >> ");
									name=sc.next();
									name+=sc.nextLine();
									name=name.replaceAll(" ", "_");
									System.out.println("\nEmail (WARNING: It must be unique): ");
									email = sc.next();
									System.out.println("\nBirthday (FORMAT: YYYY-MM-DD): ");
									registration = LocalDate.now();
									try {
										birth = LocalDate.parse(sc.next());
										if(uManager.modifyUser(player, name, birth, email)==true) {System.out.println("\nUser succesifully modified\n");}
										else{ System.out.println("\nAlready existing email - Could not update the user\n");}
									}
									catch(DateTimeParseException e) {
										System.out.println("\nInvalid date format - Unable to create the new player\n");
									}	
								}
								else
								{
									System.out.println("\nUser not found\n");
								}
								break;
								
							case "3":
								System.out.println("\nUSERS");
								for(int i=0; i<uManager.listUsers().size(); i++) { 
									System.out.println(uManager.listUsers().get(i).toString());
								}
								break;
								
							case "0":
								break;
								
							default:
								System.out.println("\n~ Opcion no valida ~\n\n");	
								break;
						}
					}
				break;
				
	//_______________________________________________________________________________________________________________________________
				
				case "2":
					optFunction="1";
					while(!optFunction.equals("0")) // THE MENU FOR THE COURT MANAGER STARTS HERE
					{
						
						System.out.println("\n-------------------- MENU --------------------\n");			
						System.out.println("0) Go back\n1) Create new court\n2) Create new material\n3) Associate material to court\n4) List courts\n5) List materials\n6) List unavailable courts\n7) List fitting courts\n");
						System.out.println("----------------------------------------------\n ");	
						optFunction = sc.next();
						
						switch (optFunction) {
						
							case "1":
								System.out.println("\nInsert new court's information...");
								System.out.println("\nName (WARNING: It must be unique): ");
								name=sc.next();
								name+=sc.nextLine();
								name=name.replaceAll(" ", "_");
								System.out.println("\nStatus (0-Available, 1-Unavailable): ");
								aux = sc.next();
								if(aux.equals("0")){cStatus=Boolean.parseBoolean("True");}
								else {cStatus=Boolean.parseBoolean("False");}
								System.out.println("\nType (0-Indoors, 1-Outdoors): ");
								aux = sc.next();
								if(aux.equals("0")){cType=Boolean.parseBoolean("True");}
								else {cType=Boolean.parseBoolean("False");}
								System.out.println("\nCourt size (MINIBASKET, ADULTS, THREE_VS_THREE):");
								aux = sc.next();
								try {
									cSize = CourtSize.valueOf(aux.toUpperCase());
									System.out.println("\nMaximum number of players ");
									maxNum = Integer.parseInt(sc.next());
									if(cManager.createCourt(name, cStatus, cType, cSize, maxNum)==true) {System.out.println("\nCourt created successfully\n");}
									else {System.out.println("\nUnable to create court - Court name already exists\n");}
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("\nERROR: Unable to create court - Invalid Court Size\n");
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
									cManager.createMaterial(mType, mUsage, mStatus);
								}
								catch(IllegalArgumentException e) {
									System.out.println("\nERROR: Unable to create material - Invalid ENUM\n");
								}
								break;
								
							case "3":
								try {
									System.out.println("\nInsert ID of the material to associate: ");
									aux = sc.next();
									id = Integer.parseInt(aux);
									System.out.println("\nInsert the name of the desired court: ");
									name=sc.next();
									name+=sc.nextLine();
									name=name.replaceAll(" ", "_");
									cManager.associateMaterial(name, id);
								}	
								catch(ImpossibleToReserveException e)
								{
									e.printStackTrace();
								}
								catch(NumberFormatException e)
								{
									System.out.println("\nInvalid ID\n");
								}
								break;
								
							case "4":
								System.out.println("\nCOURTS\n");
								courts = cManager.listCourts();
								for(int i=0; i<courts.size(); i++) {
									System.out.println(courts.get(i).toString() + "\n");
								}
								break;
								
							case "5":
								System.out.println("\nMATERIALS\n");
								materials = cManager.listMaterials();
								for(int i=0; i<materials.size(); i++) {
									System.out.println(materials.get(i).toString() + "\n");
								}
								break;
								
							case "6":
								System.out.println("\nUNAVAILABLE COURTS\n");
								courts = cManager.listUnavailableCourts();
								for(int i=0; i<courts.size(); i++) {
									System.out.println(courts.get(i).toString() + "\n");
								}
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
									System.out.println("\nFITTING COURTS\n");
									courts = cManager.listFittingCourts(maxNum, cType);
									for(int i=0; i<courts.size(); i++) {
										System.out.println(courts.get(i).toString() + "\n");
									}
								}
								catch(NumberFormatException e)
								{
									System.out.println("\nImpossible to process - Non numeric number of players\n");
								}
								break;
								
							case "0":
								break;
								
							default:
								System.out.println("\n~ Opcion no valida ~\n\n");			
								break;
						}
					} // THE MENU FOR THE USER MANAGER ENDS HERE
					
				break;
				
	//______________________________________________________________________________________________________________________________
				
				case "3":

					optFunction="1";
					while(!optFunction.equals("0")) // THE MENU FOR THE USER MANAGER STARTS HERE
					{
						System.out.println("\n-------------------- MENU --------------------\n");			
						System.out.println("0) Go back\n1) Make an individual reservation\n2) Make reservations within a package\n3) Cancel a reservation\n4) Modify a reservation\\n5) Consult number of future reservations\n6) Consult future reservations\n");
						System.out.println("----------------------------------------------\n\n> ");	
						optFunction = sc.next();
						
						switch (optFunction) {
							
							case "1":
								System.out.println("\nWhat type of reservation will it be? \n0): Children\n1) Adult\n2) Family\n\n> ");
								aux = sc.next();
								if(!aux.equals("0") || !aux.equals("1") || !aux.equals("2")) {System.out.println("\nInvalid type of reservation\n");}
								System.out.println("\nEmail of the user making the reservation >> ");
								email = sc.next();
								
								break;
							case "2":
								System.out.println("\nWhat type of reservation will it be? \n0): Children\n1) Adult\n2) Family\n\n> ");
								aux = sc.next();
								if(!aux.equals("0") || !aux.equals("1") || !aux.equals("2")) {System.out.println("\nInvalid type of reservation\n");}
								System.out.println("\nEmail of the user making the reservation >> ");
								email = sc.next();
									
								break;
							case "3":
								
								break;
								
							case "4":
								break;
								
							case "5":
								break;

							case "6":
								break;
								
							case "0":
								break;
							default:
								System.out.println("\n~ Opcion no valida ~\n\n");			
								break;
						}
					} // THE MENU FOR THE USER MANAGER ENDS HERE
				
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
		
		try {
			BufferedWriter usersWriter = new BufferedWriter(new FileWriter(loader.getProperty("PlayersFile")));
			BufferedWriter courtsWriter = new BufferedWriter(new FileWriter(loader.getProperty("CourtsFile")));
			BufferedWriter materialsWriter = new BufferedWriter(new FileWriter(loader.getProperty("MaterialsFile")));
			//BufferedWriter reservationWriter = new BufferedWriter(new FileWriter(new File("src/data/reservations.txt")));
			
			for(Player u : uManager.listUsers()) {
				usersWriter.write(u.getName() + " " + u.getBirth() + " " + u.getRegistration() + " " + u.getEmail() + "\n");
			}
			for(Court c : cManager.listCourts()) {
				courtsWriter.write(c.getName() + " " + c.getStatus() + " " + c.getType() + " " + c.getSize() + " " + c.getMaxNum());
				for(Material m : c.getMaterials())
				{
					courtsWriter.write(" " + m.getId());
				}
				courtsWriter.write("\n");
			}
			for(Material m : cManager.listMaterials()) {
				materialsWriter.write(m.getType() + " " + m.getUsage() + " " + m.getStatus() + "\n");
			}
			usersWriter.close();
			courtsWriter.close();
			materialsWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Program succesfully ended...\n");	
	}
}
