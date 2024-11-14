package data.common;

import java.sql.*;

/**
 * Class in charge of getting the connection to the database
 */
public class DBConnection {

	protected Connection connection = null;
	protected String url;
	protected String user;
	protected String password;
	
	/**
	 * Tries to connect to the database
	 * @return The connection to the database
	 */
	public Connection getConnection(){
		ConfigLoader loader = new ConfigLoader();
		try{
			url = loader.getProperty("url");
			user = loader.getProperty("user");
			password = loader.getProperty("passwd");
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
		} 
		catch (SQLException e) {
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this.connection;
	}
	
	/**
	 * Closes the connection with the database
	 */
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}
