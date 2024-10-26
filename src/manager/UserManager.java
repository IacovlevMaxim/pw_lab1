package manager;

import java.time.LocalDate;
import java.util.ArrayList;

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
	
	public boolean register(Player newUser)
	{
		
		if(isRegistered(newUser.getEmail()))
		{
			
			return false;
			
		}
		
		else
		{
			
			users.add(newUser);
			return true;
			
		}
		
	}
	
	public void modifyUser(Player user, String newName, String newSurname, LocalDate newBirhDate, String newEmail)
	{
		
		System.out.println("Modify name and surname");
		user.setName(newName);
		
		System.out.println("Modify birth date");
		user.setBirth(newBirhDate);
		
		System.out.println("Modify email");
		user.setEmail(newEmail);
		
	}
	
	public ArrayList<Player> listUsers()
	{
		
		ArrayList<Player> regUsers = new ArrayList<Player>();
		
		for(Player user : users)
		{
			if(isRegistered(user.getEmail()))
			{
				
				regUsers.add(user);
				
			}
			
		}
		
		return regUsers;
		
	}
	
}
