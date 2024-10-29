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
	
	/**
	 * Check if an user is registered in the system
	 * @param email
	 * @return true if the user with the email is registered, if he is not return false
	 */
	
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
	
	/**
	 * Register a new user in the system
	 * @param newUser
	 * @return True if the user can be added to the list, false if the user is already registered
	 */
	
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
	
	/**
	 * Modify a specific user by searching him
	 * @param user
	 * @param newNameSurname
	 * @param newBirthDate
	 * @param newReservation
	 * @param newEmail
	 */
	
	public void modifyUser(Player user, String newNameSurname, Date newBirthDate, Date newReservation, String newEmail)
	{
		
		for(Player u : users)
		{
			
			if(u == user)
			{
				
				user.setName_surname(newNameSurname);
				user.setBirthDate(newBirthDate);
				user.setFirstReservation(newReservation);
				user.setEmail(newEmail);
				
			}
			
		}
		
	}
	
	/**
	 * Lists all the users in the system
	 * @return A new list with the users in the system
	 */
	
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
	
	//NEW
	public Player getUser(String email) {
            for (Player user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
            return null;
        }
	
}
