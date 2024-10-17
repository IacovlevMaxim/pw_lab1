package classes;
import java.util.ArrayList;
import enums.*;

public class Court {
	private String name; 
	private boolean status;
	private boolean type;
	private CourtSize size;
	private int max_num;
	ArrayList<Material> materials;
	
	// METHODS
	
	public Court(){
		
	}
	
	public Court(String new_name, boolean status, boolean type, CourtSize size, int max_num) {
		name = new_name;
		this.status = status;
		this.type = type;
		this.size = size;
		this.max_num = max_num;
		materials = new ArrayList<Material>();
	}
	
	public String getName() { 
		return name;
	}
	
	public boolean getStatus() {
		return this.status;
	}

	public boolean getType() {
		return this.type;
	}

	public CourtSize getSize() {
		return this.size;
	}

	public int getMaxNum() {
		return this.max_num;
	}

	public ArrayList<Material> getMaterials() {
		return this.materials;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setType(boolean type) {
		this.type = type;
	}
	
	public void setSize(CourtSize size) {
		this.size = size;
	}
	
	public void setMaxNum(int max_num) {
		this.max_num = max_num;
	}
	
	public void setMaterials(ArrayList<Material> materials) { // DO WE NEED A SET_MATERIALS, OR IS IT THE ASSOCIATE
															 // MATERIAL TO TRACK FUNCTION?
		this.materials = materials;
	}
	
	public String toString() {
		String courtInfo;
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
		courtInfo += "List of materials:\n";
		
		for(int i=0; i<this.materials.size(); i++) { // FIND THE MATERIALS THAT ARE AVAILABLE
			courtInfo += this.materials.get(i).toString();
			courtInfo += "\t----------------------------------\n";
		}
		return courtInfo;
	}
	
	public ArrayList<Material> consultAvailableMaterials() {
		ArrayList<Material> availableMat = new ArrayList<Material>();
		
		for(int i=0; i<this.materials.size(); i++) { // FIND THE MATERIALS THAT ARE AVAILABLE
			if((this.materials.get(i).getStatus())==MaterialStatus.AVAILABLE) { 
				availableMat.add(this.materials.get(i));
			}
		}
		
		return availableMat;
	}
	public boolean associateMaterialToCourt(Material new_mat) {
		if(!(new_mat.getUsage()==false && new_mat.getUsage()!=this.type)){
			this.materials.add(new_mat);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String info;
		Court c = new Court("Hope&Dreams", true, false, CourtSize.ADULTS, 20);
		MaterialType aux2 = MaterialType.BALL;
		MaterialStatus aux3 = MaterialStatus.AVAILABLE;
		Material aux1 = new Material(10, aux2, true, aux3);
		c.associateMaterialToCourt(aux1);
		info = c.toString();
		System.out.println(info);
	}
	
}


// DO WE NEED A SET MATERIALS
// HOW DO WE IMPLEMENT THE MAXIMUM OF 12 BALLS 2 BASKETS AND 20 CONES
