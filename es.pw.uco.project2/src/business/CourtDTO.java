package business;

public class CourtDTO 
{

	private String name;
	private boolean status;
	private boolean type;
	private CourtSize size;
	private int maxNum;
	
	public CourtDTO() {}
	
	/**
	 * 
	 * @param name
	 * @param status, AVAILABLE or UNAVAILABLE
	 * @param type, Indoors or Outdoors
	 * @param size, MINIBASKET, ADULTS or THREE_VS_THREE
	 * @param maxNum, maximum number of players
	 */
	public CourtDTO(String name, boolean status, boolean type, CourtSize size, int maxNum)
	{
		
		this.name = name;
		this.status = status;
		this.type = type;
		this.maxNum = maxNum;
		
	}
	
	public String getName() {return this.name;}
	
	public void setName(String name) {this.name = name;}
	
	public boolean getStatus() {return this.status;}
	
	public void setStatus(boolean status) {this.status = status;}
	
	public boolean getType() {return this.type;}
	
	public void setType(boolen type) {this.type = type;}
	
	public int getMaxNum() {return this.maxNum;}
	
	public void setMaxNum(int maxNum) {this.maxNum = maxNum;}
	
	public String toString()
	{
		
		String courtInfo;
		String name=this.name.replaceAll("_", " ");
		courtInfo = "Court's name: " + name + "\n";
		if(this.type) {
			courtInfo += "Track type: Indoors\n";
		}
		else {
			courtInfo += "Track type: Outdoors\n";
		}
		
		if(this.size!=null) { 
			courtInfo += "Track size: " + this.size + "\n";
		}
		
		courtInfo += "Maximum number of players: " + this.maxNum + "\n";

		if(this.status) {
			courtInfo += "Status: Available for reservation\n";
		}
		else {
			courtInfo += "Status: Not available for reservation\n";
		}
		courtInfo += "Associated material's ID: ";
		
		//for(int i=0; i<this.materials.size(); i++) { // FIND THE MATERIALS THAT ARE AVAILABLE
		//	courtInfo += this.materials.get(i).getId() + ", ";
		//}
		return courtInfo;
		
	}
	
	/*
	public boolean associateMaterialToCourt(Material newMat)
	{
		
		if(!(newMat.getUsage()==false && newMat.getUsage()!=this.type))
		{
			
			this.materials.add(newMat);
			return true;
			
		}
		
		return false;
		
	}
	*/
	
}
