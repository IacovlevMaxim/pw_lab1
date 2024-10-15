package manager;

import java.util.ArrayList;
import java.util.Date;

import classes.Player;

public class UserManager 
{

	private ArrayList<Player> users;
	
	public UserManager()
	{
		
		users = new ArrayList<>();
		
	}
	
	public boolean isRegistered(String email)
	{
		
		for(Player user : users)
		{
			
			if(user.getEmail().equals(email))
			{
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public void register(Player newUser)
	{
		
		if(isRegistered(newUser.getEmail()))
		{
			
			System.out.println("The user is already registered.");
			
		}
		
		else
		{
			
			users.add(newUser);
			System.out.println("User succesfully registered.");
			
		}
		
	}
	
	public void modifyUser(Player user, String newName, String newSurname, Date newBirhDate, String newEmail)
	{
		
		System.out.println("Modify name and surname");
		user.setName(newName);
		user.setSurname(newSurname);
		
		System.out.println("Modify birth date");
		user.setBirthDate(newBirhDate);
		
		System.out.println("Modify email");
		user.setEmail(newEmail);
		
	}
	
	public void listUsers()
	{
		
		for(Player user : users)
		{
			
			user.getName();
			user.getSurname();
			user.getBirthDate();
			user.getFirstReservation();
			user.getEmail();
			
		}
		
	}
	
}
