package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import business.UserDTO;
import data.common.DBConnection;
import data.common.QueriesLoader;

public class UserDAO {

	QueriesLoader loader;
	
	/**
	 * Empty constructor
	 */
	public UserDAO(){
		loader = new QueriesLoader();
	}
	
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
}
