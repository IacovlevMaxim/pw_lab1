package data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import business.MaterialDTO;
import business.enums.*;
import business.exceptions.ImpossibleToReserveException;
import business.exceptions.MaterialNotFoundException;
import data.common.DBConnection;
import data.common.QueriesLoader;

/**
 * Data access object for the Materials
 */
public class MaterialDAO {

	QueriesLoader loader;
	
	/**
	 * Empty constructor
	 */
	public MaterialDAO()
	{
		loader = new QueriesLoader();
	}
	
	/**
	 * Gets a material from the database using the id
	 * @param id Id of the material to request
	 * @return The material, null if it was not found
	 */
	public MaterialDTO requestMaterialById(Integer id){
		MaterialDTO material = new MaterialDTO();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("MaterialIdFilter"));
			stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	return null;
            }
            
            MaterialType type=MaterialType.valueOf(rs.getString("type"));
            MaterialStatus status=MaterialStatus.valueOf(rs.getString("status"));
            boolean usage=rs.getBoolean("usg");
            Integer courtId = rs.getInt("court_id");
            material = new MaterialDTO(id, type, usage, status, courtId);
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return material;
	}
	
	/**
	 * Requests all materials
	 * @return A list with all the materials
	 */
	public ArrayList<MaterialDTO> requestAllMaterials(){
		ArrayList<MaterialDTO> materials = new ArrayList<MaterialDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("MaterialsAllFilter"));
			
            ResultSet rs = stmt.executeQuery();
			
            while(rs.next())
            {
            	Integer id=rs.getInt("id");
                MaterialType type=MaterialType.valueOf(rs.getString("type"));
                MaterialStatus status=MaterialStatus.valueOf(rs.getString("status"));
                boolean usage=rs.getBoolean("usg");
                Integer courtId = rs.getInt("court_id");

                materials.add(new MaterialDTO(id, type, usage, status, courtId));
            }
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return materials;
	}
	
	/**
	 * Get all the materials reserved in a court, using the id of the court
	 * @param courtId Id of the court
	 * @return A list with all the materials reserved in that court
	 */
	public ArrayList<MaterialDTO> requestCourtMaterials(int courtId) {
		ArrayList<MaterialDTO> materials = new ArrayList<MaterialDTO>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("MaterialsCourtFilter"));
			stmt.setInt(1, courtId);
			
            ResultSet rs = stmt.executeQuery();
			
            while(rs.next())
            {
            	Integer id=rs.getInt("id");
                MaterialType type=MaterialType.valueOf(rs.getString("type"));
                MaterialStatus status=MaterialStatus.valueOf(rs.getString("status"));
                boolean usage=rs.getBoolean("usg");
                Integer cId = rs.getInt("court_id");

                materials.add(new MaterialDTO(id, type, usage, status, cId));
            }
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return materials;
	}
	
	/**
	 * Reserves a material
	 * @param id Id of the material to reserve
	 * @param courtId Id of the court to associate to the material
	 * @throws MaterialNotFoundException
	 * @throws ImpossibleToReserveException
	 */
	public void reserveMaterialById(Integer id, int courtId) throws MaterialNotFoundException, ImpossibleToReserveException {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("MaterialIdFilter"));
			
			stmt.setInt(1, id);
			
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	throw new MaterialNotFoundException("That id does not belong to any material. Unable to reserve material.\n");
            }
            
            MaterialStatus status=MaterialStatus.valueOf(rs.getString("status"));
            if(status==MaterialStatus.RESERVED)
            {
            	throw new ImpossibleToReserveException("The material is alreay reserved. Unable to reserve material.\n");
            }
            else if(status==MaterialStatus.BAD_CONDITION)
            {
            	throw new ImpossibleToReserveException("The material currently under maintenance. Unable to reserve material.\n");
            }
            else
            {
            	stmt = connection.prepareStatement(loader.getProperty("ReserveMaterial"));
    			stmt.setInt(1, courtId);
    			stmt.setInt(2, id);
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
	 * Adds a new material to the database
	 * @param material The material to add
	 */
	public void addNewMaterial(MaterialDTO material){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("InsertNewMaterial"));
			
			stmt.setString(1, material.getType().name());
			stmt.setInt(2, material.getUsage() ? 1 : 0);
			stmt.setString(3, material.getStatus().name());
			
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
	 * Count the number of materials of a specific type in a court
	 * @param type Type of the material
	 * @param courtId Id of the court
	 * @return The number of materials
	 */
	public int countMaterialsOfTypeInCourt(MaterialType type, int courtId)
	{
		int count=0;
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(loader.getProperty("CountMaterialsCourtType"));
			stmt.setInt(1, courtId);
			stmt.setString(2, type.name());
            ResultSet rs = stmt.executeQuery();
			
            if(!rs.next()) {
            	return 0;
            }

            count=rs.getInt("materialCount");
            
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		
		}catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return count;
	}

}
