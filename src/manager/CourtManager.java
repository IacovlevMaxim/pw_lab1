package manager;

import java.util.ArrayList;
import classes.Court;
import enums.CourtSize;
import classes.Material;
import enums.MaterialStatus;
import enums.MaterialType;
import exceptions.ImpossibleToReserveException;

public class CourtManager {

		private ArrayList<Court> listOfCourts;
		private ArrayList<Material> listOfMaterials;
		
		/**
		 * Empty constructor
		 */
		public CourtManager() // UPDATEEEEEEEEEEEEEEEEEEEE
		{
			
			listOfCourts = new ArrayList<Court>();
			listOfMaterials = new ArrayList<Material>();
			
		}
	
		/**
		 * Creates a court using the IDs of the materials assigned to it
		 * @param name Name of the court
		 * @param status Status of the court
		 * @param type Type of the court (Indoors/Outdoors)
		 * @param size Size of the court
		 * @param max_num Maximum number of people in the court
		 * @param ids IDs of the materials assigned to the court
		 * @return True if the creation was successful, False if the name of the court already exists
		 */
		public boolean createCourt(String name, boolean status, boolean type, CourtSize size, int max_num, ArrayList<Integer> ids) {
			
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getName()==name){
					return false; // THE NAME ALREADY EXISTS, COULD NOT CREATE THE COURT
				}
			}
			ArrayList<Material> materials = new ArrayList<Material>();
			Court court = new Court(name, status, type, size, max_num);
			for(Integer s : ids)
			{
				materials.add(listOfMaterials.get(s));
			}
			court.setMaterials(materials);
			this.listOfCourts.add(court); 
			return true;
		}
		
		/**
		 * Creates a court without materials
		 * @param name Name of the court
		 * @param status Status of the court
		 * @param type Type of the court (Indoors/Outdoors)
		 * @param size Size of the court
		 * @param max_num Maximum number of people in the court
		 * @return True if the creation was successful, False if the name of the court already exists
		 */
		public boolean createCourt(String name, boolean status, boolean type, CourtSize size, int max_num) {
			
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getName()==name){
					return false; // THE NAME ALREADY EXISTS, COULD NOT CREATE THE COURT
				}
			}
			System.out.println("\nHOLAAAAAAAAAAA");
			Court court = new Court(name, status, type, size, max_num);
			this.listOfCourts.add(court); 
			return true;
		}
		
		/**
		 * Creates a material
		 * @param type Type of the material (BALL, BASKET, CONE)
		 * @param usage Usage of the material (true: Indoors, False: Outdoors)
		 * @param status Status of the material (AVAILABLE, RESERVED, BAD_CONDITION)
		 */
		public void createMaterial(MaterialType type, boolean usage, MaterialStatus status) {
			int lastIndex = this.listOfMaterials.size();
			Material mat = new Material(lastIndex, type, usage, status);
			listOfMaterials.add(mat);
		}
		
		/**
		 * Associates a existing material to an existing court 
		 * @param name Name of the court
		 * @param id ID of the material to associate
		 * @return True if the operation was successful, False if it does not fit the Indoors/Outdoors requirements
		 * @throws ImpossibleToReserveException Throws an exception if it doesn't fit any of the conditions such as the maximum number of materials in the court, the material or
		 * the court are not available, or if material or the court do not exist
		 */
		public boolean associateMaterial(String name, Integer id) throws ImpossibleToReserveException {
			boolean res;
			int quantity;
			for(Material m : listOfMaterials) {
				if(m.getId()==id){
					if(m.getStatus()==MaterialStatus.BAD_CONDITION) {
						throw new ImpossibleToReserveException("Impossible to reserve the material - Material currently under maintenance.");
					}
					else if(m.getStatus()==MaterialStatus.RESERVED) {
						throw new ImpossibleToReserveException("Impossible to reserve the material - Material already reserved.");
					}
					for(Court c : listOfCourts) {
						if(c.getName().equals(name)){
							if(c.getStatus()==false) {
								throw new ImpossibleToReserveException("Impossible to reserve the material - Court not available.");
							}
							quantity=this.countReservedMaterials(m.getType(), c.getMaterials()); // WE COUNT THE AMOUNT OF MATERIALS OF THAT TYPE
							if(m.getType()==MaterialType.BALL)
							{
								if(quantity>12) {
									throw new ImpossibleToReserveException("Impossible to reserve the material - Number of reserved balls exceeded.");
								}
							}
							else if(m.getType()==MaterialType.BASKET)
							{
								if(quantity>2) {
									throw new ImpossibleToReserveException("Impossible to reserve the material - Number of reserved baskets exceeded.");
								}
							}
							else if(m.getType()==MaterialType.CONE)
							{
								if(quantity>20) {
									throw new ImpossibleToReserveException("Impossible to reserve the material - Number of reserved cones exceeded.");
								}
							}
							m.setStatus(MaterialStatus.RESERVED);
							res = c.associateMaterialToCourt(m);
							return res;
						}
					}
					throw new ImpossibleToReserveException("Court not found.");
				}
			}
			throw new ImpossibleToReserveException("Material not found.");
		}
		
		/**
		 * Gets the set of unavailable courts
		 * @return A list with the set of courts that are not available
		 */
		public ArrayList<Court> listUnavailableCourts() {
			ArrayList<Court> courts = new ArrayList<Court>();
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getStatus()==false) { // UNAVAILABLE COURTS
					courts.add(this.listOfCourts.get(i));
				}
			}
			return courts;
		}
		
		/**
		 * Gets the set of courts that fit a number of players and a type of court
		 * @param num Minimum number of players required
		 * @param type Type of the court
		 * @return The list with the fitting courts
		 */
		public ArrayList<Court> listFittingCourts(int num, boolean type) {
			ArrayList<Court> courts = new ArrayList<Court>();
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getMaxNum()>=num && this.listOfCourts.get(i).getType()==type && this.listOfCourts.get(i).getStatus()==true) {
					courts.add(this.listOfCourts.get(i));
				}
			}
			return courts;
		}
		
		/**
		 * Count the amount of materials of one type reserved by a court
		 * @param type Type of material we want to check for
		 * @param materials The sets of materials of the court we want to check
		 * @return The number of materials of that type in the court
		 */
		public int countReservedMaterials(MaterialType type, ArrayList<Material> materials) {
			int num=0;
			for(Material m : materials) {
				if(m.getType()==type)
				{
					num++;
				}
			}
			return num;
		}
		
		/**
		 * List the existing courts
		 * @return The list with the courts
		 */
		public ArrayList<Court> listCourts()
		{
			ArrayList<Court> courts = this.listOfCourts;
			return courts;
		}
		
		/**
		* List the existing materials
		* @return The list with the materials
		*/
		public ArrayList<Material> listMaterials()
		{
			ArrayList<Material> materials = this.listOfMaterials;
			return materials;
		}
		
}
