package business;

import business.enums.*;

/**
 * Data transfer object for the Materials
 */
public class MaterialDTO {

	private Integer id;
    private MaterialType type;
    private boolean usage;
    private MaterialStatus status;

    /**
     * Parameterized constructor
     * @param id Id of the material
     * @param type Type of material (BALL, BASKET, CONE)
     * @param usage Usage of the material (Indoors, Outdoors)
     * @param status Status of the material (AVAILABLE, RESERVED, BAD_CONDITION)
     */
    public MaterialDTO(Integer id, MaterialType type, boolean usage, MaterialStatus status) {
        this.id = id;
        this.type = type;
        this.usage = usage;
        this.status = status;
    }
    
    /**
     * Parameterized constructor without the id
     * @param type Type of material (BALL, BASKET, CONE)
     * @param usage Usage of the material (Indoors, Outdoors)
     * @param status Status of the material (AVAILABLE, RESERVED, BAD_CONDITION)
     */
    public MaterialDTO(MaterialType type, boolean usage, MaterialStatus status) {
        this.id = null; // Used when we need to save a new id in our database
        this.type = type;
        this.usage = usage;
        this.status = status;
    }

   /**
    * Empty constructor
    */
    public MaterialDTO() {

    }

    /**
     * Creates a string to print the information of the material
     * @return The string containing the information of the material
     */
    public String toString() {
        String aux = "\nId: " + this.id +
                "\nType: " + this.type;
        if(this.usage==true)
        {
        	aux += "\nUsage: Indoors";
        }
        else
        {
        	aux +="\nUsage: Outdoors";
        }
        aux += "\nStatus: " + this.status;
        return aux;
    }

    /**
     * Gets the ID of the material
     * @return The ID of the material
     */
    public Integer getId() {
        return id;
    }
    /**
     * Gets the type of the material
     * @return The type of the material
     */
    public MaterialType getType() {
        return type;
    }
    /**
     * Gets the usage of the material
     * @return The usage of the material
     */
    public boolean getUsage() {
        return usage;
    }

    /**
     * Gets the status of the material
     * @return The status of the material
     */
    public MaterialStatus getStatus() {
        return status;
    }

    /**
     * Sets the ID of the material
     * @param id The new ID of the material
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Sets the type of the material
     * @param type New type of the material
     */
    public void setType(MaterialType type) {
        this.type = type;
    }
    /**
     * Sets the usage of the material
     * @param usage New usage of the material
     */
    public void setUsage(boolean usage) {
        this.usage = usage;
    }
    /**
     * Sets the status of the material
     * @param status New status of the material
     */
    public void setStatus(MaterialStatus status) {
        this.status = status;
    }
	
}
