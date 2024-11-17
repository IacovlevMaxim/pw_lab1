package business;

import java.time.LocalDate;
import java.util.ArrayList;

import business.exceptions.*;
import data.DAO.PlayerDAO;

/**
 * Class to manage the players
 */
public class UserManager {

	private PlayerDAO playerDB;
	
	/**
	 * Empty constructor
	 */
	public UserManager()
	{
		playerDB = new PlayerDAO();
	}
	
	/**
	 * Registers a new user
	 * @param name Name of the user to register
	 * @param birth Birth of the user to register
	 * @param registration Registration date of the user to register
	 * @param email Email of the user to register
	 * @throws PlayerAlreadyExistsException
	 */
	public void register(String name, LocalDate birth, LocalDate registration, String email) throws PlayerAlreadyExistsException {
		if(playerDB.requestPlayerByEmail(email)!=null)
		{
			throw new PlayerAlreadyExistsException("The player already exists. Unable to create new player.");
		}
		else
		{
			playerDB.addNewPlayer(new PlayerDTO(name, birth, registration, email));
		}
	}
	
	/**
	 * Modify an user's information
	 * @param email Email of the player to modify
	 * @param newName New name to set
	 * @param newBirth New birthday to set
	 * @param newEmail New email to set
	 * @throws PlayerNotFoundException
	 * @throws PlayerAlreadyExistsException
	 */
	public void modifyUser(String email, String newName, LocalDate newBirth, String newEmail) throws PlayerNotFoundException, PlayerAlreadyExistsException
	{
		PlayerDTO player=playerDB.requestPlayerByEmail(email);
		if(player==null)
		{
			throw new PlayerNotFoundException("The player with that email doesn`t exists. Unable to modify user.");
		}
		else {
			if(playerDB.requestPlayerByEmail(newEmail)!=null && !email.equals(newEmail)) {
				throw new PlayerAlreadyExistsException("That email already belongs to an user. Unable to modify user.");
			}
			else
			{
				playerDB.modifyPlayer(email, new PlayerDTO(newName, newBirth, player.getRegistration(), newEmail));
			}
		}
	}
	
	/**
	 * Get the list of existing players
	 * @return The list of players
	 */
	public ArrayList<PlayerDTO> getPlayers()
	{
		ArrayList<PlayerDTO> players = playerDB.requestAllPlayers();
		return players;
	}
	
	/**
	 * Gets an user
	 * @param email Email of the user
	 * @return The user
	 */
	public PlayerDTO getPlayer(String email) {
        PlayerDTO player = playerDB.requestPlayerByEmail(email);
        if(player==null)
        {
        	return null;
        }
        return player;
    }
	
	/**
	 * List of to string methods for a list of players
	 * @param courts The list of players to get the to string methods from
	 * @return The list of strings
	 */
	public ArrayList<String> getPlayersString(ArrayList<PlayerDTO> players)
	{
		ArrayList<String> playersText = new ArrayList<String>();
		for(PlayerDTO p : players) {
			playersText.add(p.toString());
		}
		return playersText;
	}
}
