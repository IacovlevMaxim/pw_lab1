package data.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import business.PlayerDTO;
import business.exceptions.PlayerNotFoundException;
import data.common.DBConnection;
import data.common.QueriesLoader;

/**
 * Class used to access the database of the player
 */
public class PlayerDAO {
	
	QueriesLoader loader;
	
	/**
	 * Empty constructor
	 */
	public PlayerDAO(){
		loader = new QueriesLoader();
	}
	
	/**
	 * Gets a player from the database using the email
	 * @param email Email of the user to get
	 * @return
	 */
	public PlayerDTO requestPlayerByEmail(String email){
		PlayerDTO player = new PlayerDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PlayerEmailFilter"));
			stmt.setString(1, email); // Set the ? in the queries file (for the first ?, replace with email)
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	return null;
            }
            
            String name = rs.getString("fullName");
            LocalDate birth = rs.getDate("birthDate").toLocalDate();
            LocalDate registration = rs.getDate("registration").toLocalDate();
            player = new PlayerDTO(name, birth, registration, email);
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return player;
	}
	
	/**
	 * Returns all players in the database
	 * @return The list with all the players in the database
	 */
	public ArrayList<PlayerDTO> requestAllPlayers(){
		ArrayList<PlayerDTO> players = new ArrayList<PlayerDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PlayersAllFilter"));
			
            ResultSet rs = stmt.executeQuery();
			
            while(rs.next())
            {
                String name = rs.getString("fullName");
                LocalDate birth = rs.getDate("birthDate").toLocalDate();
                LocalDate registration = rs.getDate("registration").toLocalDate();
                String email = rs.getString("email");

                players.add(new PlayerDTO(name, birth, registration, email));
            }
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return players;
	}
	
	/**
	 * Add a new player
	 * @param player Player to add
	 */
	public void addNewPlayer(PlayerDTO player){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("InsertNewPlayer"));
			
			stmt.setString(1, player.getName()); // Set the ? in the queries file (for the first ?, replace with email)
            Date date = Date.valueOf(player.getBirth());
			stmt.setDate(2, date);
			date = Date.valueOf(player.getRegistration());
			stmt.setDate(3, date);
			stmt.setString(4, player.getEmail());
			
			stmt.executeUpdate();
			
			if (stmt != null){ 
				stmt.close(); 
			}
			
			dbConnection.closeConnection();
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		
		}
	}
	

	/**
	 * Deletes a player using the email
	 * @param email Email of the user to delete
	 * @throws PlayerNotFoundException
	 */
	public void deletePlayer(String email) throws PlayerNotFoundException {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("DeletePlayer"));
			
			stmt.setString(1, email);
			int rs=stmt.executeUpdate();

			if(rs==0) {
            	throw new PlayerNotFoundException("That email does not belong to any player\n");
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		
		}
	}
	
	/**
	 * Modifies a player's information
	 * @param email Email of the player to modify
	 * @param player New information of the player
	 * @return true if the operation was successful, false if the player was not found
	 */
	public boolean modifyPlayer(String email, PlayerDTO player) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("ModifyPlayer"));

			stmt.setString(3, player.getName()); // Set the ? in the queries file (for the first ?, replace with email)
            Date date = Date.valueOf(player.getBirth());
			stmt.setDate(2, date);
			stmt.setString(1, player.getEmail());
			stmt.setString(4, email);
			
			int rs=stmt.executeUpdate();

			if(rs==0) {
            	return false;
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		
		}
		return true;
	}
	
}
