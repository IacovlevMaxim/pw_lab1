package data.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import business.PackageDTO;
import data.common.DBConnection;
import data.common.QueriesLoader;

/**
 * Data access object for the packages
 */
public class PackageDAO {

	QueriesLoader loader;
	
	/**
	 * Empty constructor
	 */
	public PackageDAO()
	{
		loader = new QueriesLoader();
	}
	
	/**
	 * Gets a package given an id
	 * @param id Id of the package to get
	 * @return The package if it exists, null if no package with that id exists
	 */
	public PackageDTO requestPackageById(Integer id){
		PackageDTO pack = new PackageDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PackageIdFilter"));
			stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	return null;
            }
            
        	int userId=rs.getInt("userId");
        	LocalDate startDate=rs.getDate("startDate").toLocalDate();
        	pack=new PackageDTO(id, userId, startDate);
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return pack;
	}
	
	/**
	 * Gets a package referencing the id of the user who made the reservation
	 * @param userId User of the id who made the reservation
	 * @return The list of packages created by that user
	 */
	public ArrayList<PackageDTO> requestPackagesByUserId(Integer userId){
		ArrayList<PackageDTO> packs = new ArrayList<PackageDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("PackageUserIdFilter"));
			stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
			
            while(rs.next())
            {
            	Integer id=rs.getInt("id");
            	LocalDate startDate=rs.getDate("startDate").toLocalDate();
                packs.add(new PackageDTO(id, userId, startDate));
            }
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return packs;
	}
	
	/**
	 * Creates a new package
	 * @param pack Information of the package to create
	 */
	public void addNewPackage(PackageDTO pack){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("InsertNewPackage"));

			stmt.setInt(1, pack.getUserId());
			stmt.setDate(2, Date.valueOf(pack.getStartDate()));
			stmt.setDate(3, Date.valueOf(pack.getExpiration()));
			
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
}
