package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import business.UserDTO;
import data.common.DBConnection;
import data.common.QueriesLoader;

/**
 * DAO for the login information of the users
 */
public class UserDAO {

	QueriesLoader loader;
	
	/**
	 * Empty constructor
	 */
	public UserDAO(){
		loader = new QueriesLoader();
	}
	
	/**
	 * Gets an user using the email, from the databse
	 * @param email Email of the user
	 * @return The user information
	 */
	public UserDTO requestUserByEmail(String email){
		UserDTO user = new UserDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("UserEmailFilter"));
			stmt.setString(1, email); // Set the ? in the queries file (for the first ?, replace with email)
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	return null;
            }
            
            String password = rs.getString("password");
            Boolean type = rs.getBoolean("type");
            user = new UserDTO(email, password, type);
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Returns all users in the database
	 * @return The list with all the users in the database
	 */
	public ArrayList<UserDTO> requestAllUsers(){
		ArrayList<UserDTO> users = new ArrayList<UserDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("UsersAllFilter"));
			
            ResultSet rs = stmt.executeQuery();
			
            while(rs.next())
            {
            	String password = rs.getString("password");
                String email = rs.getString("email");
                Boolean type = rs.getBoolean("type");

            	users.add(new UserDTO(email, password, type));
            }
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return users;
	}
}
