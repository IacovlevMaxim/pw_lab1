package data.DAO;

import java.sql.*;
import java.util.ArrayList;

import business.CourtDTO;
import business.enums.CourtSize;
import data.common.QueriesLoader;
import data.common.DBConnection;
import business.exceptions.CourtNotFoundException;
import business.exceptions.ImpossibleToReserveException;

/**
 * Data access object for the courts
 */
public class CourtDAO {
	
		QueriesLoader loader;
		
		/**
		 * Empty constructor
		 */
		public CourtDAO() {
			
			loader = new QueriesLoader();
		}
		
		/**
		 * Gets a court from the database by the name
		 * @param name Name of the court
		 * @return The court if it was found, null if it wasn't
		 */
		public CourtDTO requestCourtByName(String name)
		{
			CourtDTO court = new CourtDTO();
			
			try
			{
				
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("CourtNameFilter"));
				stmt.setString(1, name);
				ResultSet rs = stmt.executeQuery();
				
				if(!rs.next()) {return null;}
				
				int id = rs.getInt("id");
				boolean status = rs.getBoolean("status");
				boolean type = rs.getBoolean("type");
				int maxNum = rs.getInt("max_players");
                CourtSize size = CourtSize.valueOf(rs.getString("size"));
				
				court = new CourtDTO(id, name, status, type, size, maxNum);
				
				if(stmt != null) {stmt.close();}
				
				dbc.closeConnection();
				
			}
			
			catch(Exception e)
			{
				
				System.err.println(e);
				e.printStackTrace();
				
			}
			
			return court;
			
		}
		
		/**
		 * Gets all courts from the database
		 * @return The list with all courts
		 */
		public ArrayList<CourtDTO> requestAllCourts()
		{
			ArrayList<CourtDTO> courts = new ArrayList<CourtDTO>();
			
			try 
			{
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("CourtsAllFilter"));
				ResultSet rs = stmt.executeQuery();
				
	            while(rs.next())
	            {
					int id = rs.getInt("id");
	                String name = rs.getString("name");
	                boolean status = rs.getBoolean("status");
	                boolean type = rs.getBoolean("type");
	                CourtSize size = CourtSize.valueOf(rs.getString("size"));
	                int maxNum = rs.getInt("max_players");

	                courts.add(new CourtDTO(id, name, status, type, size, maxNum));
	            }
	            
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
			
			}
			
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
			
			return courts;
			
		}
		
		/**
		 * Adds a new court to the database
		 * @param court Information of the court to add
		 */
		public void addNewCourt(CourtDTO court)
		{
			try 
			{
				
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("InsertNewCourt"));
				
				stmt.setString(1, court.getName()); // Set the ? in the queries file (for the first ?, replace with email)
	            stmt.setInt(2, court.getStatus() ? 1 : 0);
	            stmt.setInt(3, court.getType() ? 1 : 0);
	            stmt.setString(4, court.getSize().name());
				stmt.setInt(5, court.getMaxNum());
				
				stmt.executeUpdate();
				
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
			}
			
			catch (Exception e)
			{
				
				System.err.println(e);
				e.printStackTrace();
			
			}
			
		}
		
		/**
		 * Deletes a court from the database
		 * @param name Name of the court to delete
		 * @throws CourtNotFoundException
		 */
		public void deleteCourt(String name) throws CourtNotFoundException 
		{
			
			try 
			{
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("DeleteCourt"));
				stmt.setString(1, name);
				int rs = stmt.executeUpdate();

				if(rs==0) 
				{
					
	            	throw new CourtNotFoundException("That name does not belong to any court\n");
				
				}
				
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
				
			}
			
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			
			}
		
		}
		
		/**
		 * Modifies a court from the database
		 * @param name Name of the court to modify
		 * @param court New information of the court
		 * @return true if the modification was successful, false otherwise
		 */
		public boolean modifyCourt(String name, CourtDTO court) 
		{
			
			try 
			{

				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("ModifyCourt"));

				stmt.setString(1, court.getName()); 
				stmt.setBoolean(2, court.getStatus());
	            stmt.setBoolean(3, court.getType());
				stmt.setInt(4, court.getMaxNum());
	            
				int rs=stmt.executeUpdate();

				if(rs==0) {return false;}
				
				if(stmt != null) {stmt.close();}
				
				dbc.closeConnection();
				
			}
			
			catch(Exception e)
			{
				
				System.err.println(e);
				e.printStackTrace();
			
			}
			
			return true;
		}
		
		/**
		 * Reserves a court for a reservation
		 * @param name Name of the court to reserve
		 * @throws CourtNotFoundException
		 * @throws ImpossibleToReserveException
		 */
		public void reserveCourtByName(String name) throws CourtNotFoundException, ImpossibleToReserveException {
			try {
				DBConnection dbConnection = new DBConnection();
				Connection connection = dbConnection.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("CourtNameFilter"));
				
				stmt.setString(1, name);
				
	            ResultSet rs = stmt.executeQuery();
				
	            if(!rs.next()) {
	            	throw new CourtNotFoundException("That ID does not belong to any court. Unable to reserve court.\n");
	            }

                boolean status = rs.getBoolean("status");
	            
	            if(!status)
	            {
	            	throw new ImpossibleToReserveException("The court is alreay reserved. Unable to reserve court.\n");
	            }
	            else
	            {
	            	stmt = connection.prepareStatement(loader.getProperty("ReserveCourt"));
	    			stmt.setString(1, name);
	                stmt.executeUpdate();
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
		 * Gets all fitting court from the database
		 * @param maxNum Number of players to fit
		 * @param size Size of the desired courts
		 * @return The list of courts that fit that criteria
		 */
		public ArrayList<CourtDTO> requestFittingCourts(int maxNum, CourtSize size)
		{
			ArrayList<CourtDTO> courts = new ArrayList<CourtDTO>();
			try 
			{
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("FittingCourtsFilter"));
	            stmt.setInt(1, maxNum);
	            stmt.setString(2, size.name());
				
				ResultSet rs = stmt.executeQuery();
				
	            while(rs.next())
	            {
					int id = rs.getInt("id");
	                String name = rs.getString("name");
	                boolean status = rs.getBoolean("status");
	                boolean type = rs.getBoolean("type");
	                int num = rs.getInt("max_players");

	                courts.add(new CourtDTO(id, name, status, type, size, num));
	            }
	            
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
			
			}
			
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
			
			return courts;
			
		}
		
		/**
		 * Gets all fitting and available courts from the database
		 * @param maxNum Number of players to fit
		 * @param size Size of the desired courts
		 * @return The list of courts that fit that criteria
		 */
		public ArrayList<CourtDTO> requestFittingAvailableCourts(int maxNum, CourtSize size)
		{
			ArrayList<CourtDTO> courts = new ArrayList<CourtDTO>();
			try 
			{
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("FittingAvailableCourtsFilter"));
	            stmt.setInt(1, maxNum);
	            stmt.setString(2, size.name());
				
				ResultSet rs = stmt.executeQuery();
				
	            while(rs.next())
	            {
					int id = rs.getInt("id");
	                String name = rs.getString("name");
	                boolean status = rs.getBoolean("status");
	                boolean type = rs.getBoolean("type");
	                int num = rs.getInt("max_players");

	                courts.add(new CourtDTO(id, name, status, type, size, num));
	            }
	            
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
			
			}
			
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
			
			return courts;
			
		}
			
		/**
		 * Gets a list with all the unavailable courts in the database
		 * @return The list with the unavailable courts
		 */
		public ArrayList<CourtDTO> requestUnavailableCourts()
		{
			ArrayList<CourtDTO> courts = new ArrayList<CourtDTO>();
			
			try 
			{
				DBConnection dbc = new DBConnection();
				Connection connection = dbc.getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(loader.getProperty("CourtsUnavailableFilter"));
	            stmt.setInt(1, 0);
				
				ResultSet rs = stmt.executeQuery();
				
	            while(rs.next())
	            {
					int id = rs.getInt("id");
	                String name = rs.getString("name");
	                boolean type = rs.getBoolean("type");
	                CourtSize size = CourtSize.valueOf(rs.getString("size"));
	                int maxNum = rs.getInt("max_players");

	                courts.add(new CourtDTO(id, name, false, type, size, maxNum));
	            }
	            
				if (stmt != null) {stmt.close();}
				
				dbc.closeConnection();
			
			}
			
			catch (Exception e)
			{
				System.err.println(e);
				e.printStackTrace();
			}
			
			return courts;
			
		}
	}


