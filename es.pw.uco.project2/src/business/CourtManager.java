package business;

import java.util.ArrayList;

import data.DAO.CourtDAO;
import data.DAO.MaterialDAO;
import business.enums.*;
import business.exceptions.*;

public class CourtManager {

	private MaterialDAO materialDB;
	private CourtDAO courtDB;
	
	/**
	 * Empty constructor
	 */
	public CourtManager() {
		materialDB = new MaterialDAO();
		courtDB = new CourtDAO();
	}
	
	/**
	 * Creates a court without materials
	 * @param name Name of the court
	 * @param status Status of the court
	 * @param type Type of the court (Indoors/Outdoors)
	 * @param size Size of the court
	 * @param maxNum Maximum number of people in the court
	 * @return True if the creation was successful, False if the name of the court already exists
	 * @throws CourtAlreadyExistsException 
	 */
	public void createCourt(String name, boolean status, boolean type, CourtSize size, int maxNum) throws CourtAlreadyExistsException {
		if(courtDB.requestCourtByName(name)!=null)
		{
			throw new CourtAlreadyExistsException("The player already exists. Unable to create new player.");
		}
		else
		{
			courtDB.addNewCourt(new CourtDTO(name, status, type, size, maxNum));
		}
	}
	
	/**
	 * Creates a material
	 * @param type Type of the material (BALL, BASKET, CONE)
	 * @param usage Usage of the material (true: Indoors, False: Outdoors)
	 * @param status Status of the material (AVAILABLE, RESERVED, BAD_CONDITION)
	 */
	public void createMaterial(MaterialType type, boolean usage, MaterialStatus status) {
		materialDB.addNewMaterial(new MaterialDTO(type, usage, status, null));
	}
	
	/**
	 * Associates an existing material to an existing court 
	 * @param name Name of the court
	 * @param id Id of the material to associate
	 * @throws ImpossibleToReserveException Throws an exception if it doesn't fit any of the conditions such as the maximum number of materials in the court, the material or
	 * the court are not available, or if material or the court do not exist
	 * @throws MaterialNotFoundException 
	 */
	public void associateMaterial(String name, Integer id) throws ImpossibleToReserveException, MaterialNotFoundException {
		int quantity;
		MaterialDTO material=materialDB.requestMaterialById(id);
		CourtDTO court=courtDB.requestCourtByName(name);
		if(material==null) // Material exists
		{
			throw new ImpossibleToReserveException("No material with that Id exists. Unable to reserve material.\n");
		}
		if(court==null) // Court exists
		{
			throw new ImpossibleToReserveException("No court with that name exists. Unable to reserve material.\n");
		}
		else
		{
			if(!court.getStatus()) // Court is available
			{
				throw new ImpossibleToReserveException("Court currently not available. Unable to reserve the material.\n");
			}
		}
		if(!material.getUsage() && material.getUsage()!=court.getType()) // Indoors and Outdoors restrictions
		{
			throw new ImpossibleToReserveException("Outdoors materials cannot be associated to indoors courts. Unable to reserve the material.\n");
		}
		quantity=materialDB.countMaterialsOfTypeInCourt(material.getType(), court.getCourtId()); // Number of materials per court
		if(material.getType().equals(MaterialType.BALL))
		{
			if(quantity>12)
			{
				throw new ImpossibleToReserveException("Number of reserved balls exceeded. Unable to reserve the material.\n");
			}
		}
		else if(material.getType().equals(MaterialType.BASKET))
		{
			if(quantity>2)
			{
				throw new ImpossibleToReserveException("Number of reserved baskets exceeded. Unable to reserve the material.\n");
			}
		}
		else if(material.getType().equals(MaterialType.CONE))
		{
			if(quantity>20)
			{
				throw new ImpossibleToReserveException("Number of reserved cones exceeded. Unable to reserve the material.\n");
			}
		}
		materialDB.reserveMaterialById(id, court.getCourtId());
	}
	
	/**
	 * Gets the set of unavailable courts
	 * @return A list with the set of courts that are not available
	 */
	public ArrayList<CourtDTO> listUnavailableCourts() {
		ArrayList<CourtDTO> courts = courtDB.requestUnavailableCourts();
		return courts;
	}
	
	/**
	 * Gets the set of courts that fit a number of players and a type of court
	 * @param num Minimum number of players required
	 * @param type Type of the court
	 * @return The list with the fitting courts
	 */
	public ArrayList<CourtDTO> listFittingCourts(int num, CourtSize size) {
		ArrayList<CourtDTO> courts = courtDB.requestFittingCourts(num, size);
		return courts;
	}
	
	/**
	 * List the existing courts
	 * @return The list with the courts
	 */
	public ArrayList<CourtDTO> getCourts()
	{
		ArrayList<CourtDTO> courts = courtDB.requestAllCourts();
		return courts;
	}
	
	/**
	* List the existing materials
	* @return The list with the materials
	*/
	public ArrayList<MaterialDTO> getMaterials()
	{
		ArrayList<MaterialDTO> materials = materialDB.requestAllMaterials();
		return materials;
	}
	

	/**
	 * Gets a list of the materials associated to a specific court
	 * @param name Name of the court
	 * @return The list with materials
	 */
	public ArrayList<MaterialDTO> getMaterialsAssociatedToCourt(String name)
	{
		CourtDTO court = courtDB.requestCourtByName(name);
		ArrayList<MaterialDTO> materials = materialDB.requestCourtMaterials(court.getCourtId());
		return materials;
	}
	
	/**
	 * Checks for the existence of a court
	 * @param name Name of the court to check
	 * @return The court if it  exists, null if it doesn't
	 */
	public CourtDTO getCourt(String name) {
		CourtDTO court = courtDB.requestCourtByName(name);
		return court;	
	}
	
	/**
	 * List of to string methods for a list of materials
	 * @param materials The list of materials to get the to string methods from
	 * @return The list of strings
	 */
	public ArrayList<String> getMaterialsString(ArrayList<MaterialDTO> materials)
	{
		ArrayList<String> materialsText = new ArrayList<String>();
		for(MaterialDTO m : materials) {
			materialsText.add(m.toString());
		}
		return materialsText;
	}
	
	/**
	 * List of to string methods for a list of courts
	 * @param courts The list of courts to get the to string methods from
	 * @return The list of strings
	 */
	public ArrayList<String> getCourtsString(ArrayList<CourtDTO> courts)
	{
		ArrayList<String> courtsText = new ArrayList<String>();
		for(CourtDTO c : courts) {
			courtsText.add(c.toString());
		}
		return courtsText;
	}
}
