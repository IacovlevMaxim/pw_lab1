package data.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import business.PlayerDTO;
import data.common.DBConnection;
import data.common.QueriesLoader;

public class PlayerDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public PlayerDTO requestPlayerByEmail(String email){
		PlayerDTO player = new PlayerDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			QueriesLoader loader = new QueriesLoader();
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PlayerEmailFilter"));
			stmt.setString(1, email); // Set the ? in the queries file (for the first ?, replace with email)
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next())
            {
            	return null;
            }
            
            String name = rs.getString("name");
            LocalDate birth = rs.getDate("birth").toLocalDate();
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
	
	public ArrayList<PlayerDTO> requestAllPlayers(){
		ArrayList<PlayerDTO> players = new ArrayList<PlayerDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			QueriesLoader loader = new QueriesLoader();
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PlayersAllFilter"));
            ResultSet rs = stmt.executeQuery();
			
            while(!rs.next())
            {
                String name = rs.getString("name");
                LocalDate birth = rs.getDate("birth").toLocalDate();
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

}
