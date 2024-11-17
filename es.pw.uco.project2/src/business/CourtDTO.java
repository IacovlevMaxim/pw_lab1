package business;

import business.enums.CourtSize;

/**
 * Data transfer object for the court
 */
public class CourtDTO {
	
	private Integer id;
	private String name;
	private boolean status;
	private boolean type;
	private CourtSize size;
	private int maxNum;
	
	/**
	 * Empty constructor
	 */
	public CourtDTO() {}
	
	/**
	 * Parameterized constructor
	 * @param name Name of the court
	 * @param status, AVAILABLE or UNAVAILABLE
	 * @param type, Indoors or Outdoors
	 * @param size, MINIBASKET, ADULTS or THREE_VS_THREE
	 * @param maxNum, maximum number of players
	 */
	public CourtDTO(String name, boolean status, boolean type, CourtSize size, int maxNum)
	{
		this.id = null;
		this.name = name;
		this.status = status;
		this.type = type;
		this.size = size;
		this.maxNum = maxNum;
	}

	/**
	 * Parameterized constructor
	 * @param id Id of the court
	 * @param name Name of the court
	 * @param status AVAILABLE or UNAVAILABLE
	 * @param type Indoors or Outdoors
	 * @param size MINIBASKET, ADULTS or THREE_VS_THREE
	 * @param maxNum maximum number of players
	 */
	public CourtDTO(Integer id, String name, boolean status, boolean type, CourtSize size, int maxNum)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.type = type;
		this.size = size;
		this.maxNum = maxNum;
		
	}
	
	/**
	 * Gets the id of the court
	 * @return The id of the court
	 */
	public int getCourtId() {
		return id;
	}

	/**
	 * Sets the id of the court
	 * @param courtId The new id of the court
	 */
	public void setCourtId(int courtId) {
		this.id = courtId;
	}
	/**
	 * Gets the name of the court
	 * @return The name of the court
	 */
	public String getName() {return this.name;}
	/**
	 * Sets the name of the court
	 * @param name The new name of the court
	 */
	public void setName(String name) {this.name = name;}
	/**
	 * Gets the status of the court
	 * @return The status of the court
	 */
	public boolean getStatus() {return this.status;}
	/**
	 * Sets the status of the court
	 * @param status The new status of the court
	 */
	public void setStatus(boolean status) {this.status = status;}
	/**
	 * Gets the type of court
	 * @return The type of court
	 */
	public boolean getType() {return this.type;}
	/**
	 * Sets the type of court
	 * @param type The new type of court
	 */
	public void setType(boolean type) {this.type = type;}
	/**
	 * Gets the maximum number of players in the court
	 * @return The new maximum number of players
	 */
	public int getMaxNum() {return this.maxNum;}
	/**
	 * Sets the maximum number of players in the court
	 * @param maxNum The new maximum number of players in the court
	 */
	public void setMaxNum(int maxNum) {this.maxNum = maxNum;}
	/**
	 * Gets the size of the court
	 * @return The size of the court
	 */
	public CourtSize getSize() {
		return size;
	}
	/**
	 * Sets the size of the court
	 * @param size The new size of the court
	 */
	public void setSize(CourtSize size) {
		this.size = size;
	}	
	
	/**
	 * To string method
	 */
	public String toString()
	{
		
		String courtInfo;
		courtInfo = "\nCourt's ID: " + this.getCourtId();
		courtInfo += "\nCourt's name: " + this.name + "\n";
		if(this.type) {
			courtInfo += "Type: INDOORS\n";
		}
		else {
			courtInfo += "Type: OUTDOORS\n";
		}
		
		courtInfo += "Size: " + this.size.name() + "\n";
		courtInfo += "Maximum number of players: " + this.maxNum + "\n";

		if(this.status) {
			courtInfo += "Status: AVAILABLE FOR RESERVATION";
		}
		else {
			courtInfo += "Status: NOT AVAILABLE FOR RESERVATION";
		}
		return courtInfo;
	}

}
