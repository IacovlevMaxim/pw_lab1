package manager;

import java.util.ArrayList;
import classes.Court;
import enums.CourtSize;
import classes.Material;
import enums.MaterialStatus;
import enums.MaterialType;

public class courtManager {

		private ArrayList<Court> listOfCourts;
		private ArrayList<Material> listOfMaterials;
	
		public boolean createCourt(String name, boolean status, boolean type, CourtSize size, int max_num) {
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getName()==name){
					return false; // THE NAME ALREADY EXISTS, COULD NOT CREATE THE COURT
				}
			}
			Court court = new Court(name, status, type, size, max_num);
			this.listOfCourts.add(court); 
			return true;
		}
		
		public void createMaterial(MaterialType type, boolean usage, MaterialStatus status) {
			int lastIndex = this.listOfMaterials.size();
			Material mat = new Material(lastIndex, type, usage, status);
			listOfMaterials.add(mat);
		}
		
		public boolean associateMaterial(String name, Integer id) {
			boolean res;
			int quantity;
			for(int i=0; i<this.listOfMaterials.size(); i++) {
				if(this.listOfMaterials.get(i).getId()==id){
					quantity=this.countReservedMaterials(this.listOfMaterials.get(i).getType()); // WE COUNT THE AMOUNT OF MATERIALS OF THAT TYPE
					if(this.listOfMaterials.get(i).getStatus()==MaterialStatus.BAD_CONDITION) {
						throw new ArithmeticException("Impossible to reserve the material - Material currently under maintenance.");
					}
					if(this.listOfMaterials.get(i).getStatus()==MaterialStatus.RESERVED) {
						throw new ArithmeticException("Impossible to reserve the material - Material already reserved.");
					}
					if(this.listOfMaterials.get(i).getType()==MaterialType.BALL)
					{
						if(quantity>12) {
							throw new ArithmeticException("Impossible to reserve the material - Number of reserved balls exceeded.");
						}
					}
					else if(this.listOfMaterials.get(i).getType()==MaterialType.BASKET)
					{
						if(quantity>2) {
							throw new ArithmeticException("Impossible to reserve the material - Number of reserved baskets exceeded.");
						}
					}
					else if(this.listOfMaterials.get(i).getType()==MaterialType.CONE)
					{
						if(quantity>20) {
							throw new ArithmeticException("Impossible to reserve the material - Number of reserved cones exceeded.");
						}
					}
					for(int j=0; j<this.listOfCourts.size(); j++) {
						if(this.listOfCourts.get(j).getName()==name){
							if(this.listOfCourts.get(j).getStatus()==false) {
								throw new ArithmeticException("Impossible to reserve the material - Court not available.");
							}
							res = this.listOfCourts.get(j).associateMaterialToCourt(this.listOfMaterials.get(i));
							return res;
						}
					}
				}
			}
			throw new ArithmeticException("Court or material not found.");
		}
		
		public ArrayList<String> listUnavailableCourts() {
			ArrayList<String> courts = new ArrayList<String>();
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getStatus()==false) { // UNAVAILABLE COURTS
					courts.add(this.listOfCourts.get(i).getName());
				}
			}
			return courts;
		}
		
		public ArrayList<Court> listFittingCourts(int num, boolean type) {
			ArrayList<Court> courts = new ArrayList<Court>();
			for(int i=0; i<this.listOfCourts.size(); i++) {
				if(this.listOfCourts.get(i).getMaxNum()>=num && this.listOfCourts.get(i).getType()==type && this.listOfCourts.get(i).getStatus()==true) {
					courts.add(this.listOfCourts.get(i));
				}
			}
			return courts;
		}
		
		
		public int countReservedMaterials(MaterialType type) {
			int num=0;
			for(int i=0; i<this.listOfMaterials.size(); i++) {
				if(this.listOfMaterials.get(i).getType()==type && this.listOfMaterials.get(i).getStatus()==MaterialStatus.RESERVED) {
					num++;
				}
			}
			return num;
		}
		
}
