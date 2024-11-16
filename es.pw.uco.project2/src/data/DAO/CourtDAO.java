package data.DAO;

import java.sql.*;
import java.util.ArrayList;

import business.CourtDTO;
import data.common.QueriesLoader;
import data.common.DBConnection;
import business.exceptions.CourtNotFoundException;

public class CourtDAO 
{
	
	QueriesLoader loader;
	
	public CourtDAO()
	{
		
		loader = new QueriesLoader();
		
	}
	
	/**
	 * Gets a court from the database by the name
	 * @param name of the court
	 * @return 
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
			
			boolean status = rs.getBoolean("status");
			boolean type = rs.getBoolean("type");
			int maxNum = rs.getInt("maxNum");
			
			court = new CourtDTO(name, status, type, maxNum);
			
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
	 * @return a list with all courts
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
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                boolean type = rs.getBoolean("type");
                int maxNum = rs.getInt("maxNum");

                courts.add(new CourtDTO(name, status, type, maxNum));
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
	 * @param court
	 */
	
	public void addNewCourt(CourtDTO court)
	{
		
		try 
		{
			
			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("InsertNewCourt"));
			
			stmt.setString(1, court.getName()); // Set the ? in the queries file (for the first ?, replace with email)
            stmt.setBoolean(2, court.getStatus());
            stmt.setBoolean(3, court.getType());
			stmt.setInt(4, court.getMaxNum());
			
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
	 * @param name
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
	 * @param name
	 * @param court
	 * @return true if the modification was succesfully
	 */
	
	public boolean modifyCourt(String name, CourtDTO court) 
	{
		
		try 
		{

			DBConnection dbc = new DBConnection();
			Connection connection = dbc.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("ModifyCourt"));

			stmt.setString(1, court.getName()); // Set the ? in the queries file (for the first ?, replace with email)
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
		
}

