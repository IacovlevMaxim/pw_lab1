package business;

import java.time.LocalDate;
import java.util.ArrayList;

import business.exceptions.*;
import data.DAO.PlayerDAO;

public class UserManager {

	/**
	 * Empty constructor
	 */
	public UserManager()
	{
		
	}
	
	/**
	 * Register a new user
	 * @param newUser User to register
	 * @return True is the operation was successful, False if the player already exists
	 * @throws PlayerAlreadyExistsException
	 */
	public void register(String name, LocalDate birth, LocalDate registration, String email) throws PlayerAlreadyExistsException {
		PlayerDAO newPlayer = new PlayerDAO();
		if(newPlayer.requestPlayerByEmail(email)!=null)
		{
			throw new PlayerAlreadyExistsException("The player already exists. Unable to create new player.");
		}
		else
		{
			newPlayer.addNewPlayer(new PlayerDTO(name, birth, registration, email));
		}
	}
	
	/**
	 * Modify an user's information
	 * @param user Player to modify
	 * @param newNameSurname New name to set
	 * @param newBirthDate New birthday to set
	 * @param newEmail New email to set
	 * @return True if the operation was successful, False if the new email already exists
	 * @throws PlayerAlreadyExistsException 
	 */
	public void modifyUser(String email, String newName, LocalDate newBirth, String newEmail) throws PlayerNotFoundException, PlayerAlreadyExistsException
	{
		PlayerDAO player = new PlayerDAO();
		PlayerDTO newPlayer=player.requestPlayerByEmail(email);
		if(newPlayer==null)
		{
			throw new PlayerNotFoundException("The player with that email doesn`t exists. Unable to modify user.");
		}
		else {
			if(player.requestPlayerByEmail(newEmail)!=null && !email.equals(newEmail)) {
				throw new PlayerAlreadyExistsException("That email already belongs to an user. Unable to modify user.");
			}
			else
			{
				player.modifyPlayer(email, new PlayerDTO(newName, newBirth, newPlayer.getRegistration(), newEmail));
			}
		}
	}
	
	/**
	 * Get the list of existing players
	 * @return The list of players
	 */
	public ArrayList<PlayerDTO> getPlayers()
	{
		PlayerDAO player = new PlayerDAO();
		ArrayList<PlayerDTO> players = player.requestAllPlayers();
		return players;
	}
	
	/**
	 * Gets an user
	 * @param email Email of the user
	 * @return The user
	 */
	public PlayerDTO getPlayer(String email) {
        PlayerDAO player = new PlayerDAO();
        PlayerDTO newPlayer = player.requestPlayerByEmail(email);
        if(newPlayer==null)
        {
        	return null;
        }
        return newPlayer;
    }
}
