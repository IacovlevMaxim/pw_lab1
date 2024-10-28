package classes;
import java.util.ArrayList;
import enums.*;

	/**
	 * Court class
	 */
public class Court {
	private String name; 
	private boolean status;
	private boolean type;
	private CourtSize size;
	private int max_num;
	ArrayList<Material> materials;
	
	// METHODS
	/**
	 * Court empty constructor
	 */
	public Court(){
		materials = new ArrayList<Material>();
	}
	
	/**
	 * Parameterized constructor
	 * @param new_name Name of the court
	 * @param status Status of the court (AVAILABLE, UNAVAILABLE)
	 * @param type Type of the court (Indoors, Outdoors)
	 * @param size Size of the court (MINIBASKET, ADULTS, THREE_VS_THREE)
	 * @param max_num Maximum number of players in a court
	 */
	public Court(String new_name, boolean status, boolean type, CourtSize size, int max_num) {
		name = new_name;
		this.status = status;
		this.type = type;
		this.size = size;
		this.max_num = max_num;
		materials = new ArrayList<Material>();
	}
	
	/**
	 * Obtains the name of the court
	 * @return The name of the court
	 */
	public String getName() { 
		return name;
	}
	/**
	 * Obtains the status of the court
	 * @return True if AVAILABLE, False if UNAVAILABLE
	 */
	public boolean getStatus() {
		return this.status;
	}
	/**
	 * Obtains the type of court
	 * @return True if Indoors, False if Outdoors
	 */
	public boolean getType() {
		return this.type;
	}
	/**
	 * Obtains the size of the court
	 * @return MINIBASKET, ADULTS or THREE_VS_THREE
	 */
	public CourtSize getSize() {
		return this.size;
	}
	
	/**
	 * Obtains the maximum number of players in the court
	 * @return The maximum number of players in the court
	 */
	public int getMaxNum() {
		return this.max_num;
	}
	/**
	 * Obtains the list of materials associated to the court
	 * @return The list of materials associated to the court
	 */
	public ArrayList<Material> getMaterials() {
		return this.materials;
	}
	
	/**
	 * Sets the name of the court
	 * @param name New name for the court
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Sets the status of the court
	 * @param status New status for the court
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Sets the type of the court
	 * @param type New type of the court
	 */
	public void setType(boolean type) {
		this.type = type;
	}
	/**
	 * Sets the size of the court
	 * @param size New size of the court
	 */
	public void setSize(CourtSize size) {
		this.size = size;
	}
	
	/**
	 * Sets the maximum number of players in the court
	 * @param max_num New maximum number of players in the court
	 */
	public void setMaxNum(int max_num) {
		this.max_num = max_num;
	}
	
	/**
	 * Sets the list of materials in the court
	 * @param materials New set of materials
	 */
	public void setMaterials(ArrayList<Material> materials) { 
		this.materials = materials;
	}
	
	/**
	 * Creates a string to print the information of the court
	 * @return The string containing the information of the court
	 */
	public String toString() {
		String courtInfo;
		String name=this.name.replaceAll("_", " ");
		courtInfo = "Court's name: " + name + "\n";
		if(this.type) {
			courtInfo += "Track type: Indoors\n";
		}
		else {
			courtInfo += "Track type: Outdoors\n";
		}
		
		if(this.size!=null) { // DO WE NEED TO SEE IF IT IS NULL TO PRINT IT??
			courtInfo += "Track size: " + this.size + "\n";
		}
		
		courtInfo += "Maximum number of players: " + this.max_num + "\n";

		if(this.status) {
			courtInfo += "Status: Available for reservation\n";
		}
		else {
			courtInfo += "Status: Not available for reservation\n";
		}
		courtInfo += "Associated material's ID: ";
		
		for(int i=0; i<this.materials.size(); i++) { // FIND THE MATERIALS THAT ARE AVAILABLE
			courtInfo += this.materials.get(i).getId() + ", ";
		}
		return courtInfo;
	}
	
	/**
	 * Obtains the list of available material in the court
	 * @return The set of available materials
	 */
	public ArrayList<Material> consultAvailableMaterials() {
		ArrayList<Material> availableMat = new ArrayList<Material>();
		
		for(int i=0; i<this.materials.size(); i++) { // FIND THE MATERIALS THAT ARE AVAILABLE
			if((this.materials.get(i).getStatus())==MaterialStatus.AVAILABLE) { 
				availableMat.add(this.materials.get(i));
			}
		}
		
		return availableMat;
	}
	
	/**
	 * Add a new material to the list of materials of the court
	 * @param new_mat The new material to add
	 * @return True if it was successfully added, False if the material's usage is outdoors (False) and the court's type is Indoors (True)  
	 */
	public boolean associateMaterialToCourt(Material new_mat) {
		if(!(new_mat.getUsage()==false && new_mat.getUsage()!=this.type)){
			this.materials.add(new_mat);
			return true;
		}
		return false;
	}

}

