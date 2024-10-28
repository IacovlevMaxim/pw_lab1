package classes;

import enums.MaterialType;
import enums.MaterialStatus;

public class Material {
    private Integer _id;
    private MaterialType _type;
    private boolean _usage;
    private MaterialStatus _status;

    /**
     * Parameterized constructor
     * @param id Id of the material
     * @param type Type of material (BALL, BASKET, CONE)
     * @param usage Usage of the material (Indoors, Outdoors)
     * @param status Status of the material (AVAILABLE, RESERVED, BAD_CONDITION)
     */
    public Material(Integer id, MaterialType type, boolean usage, MaterialStatus status) {
        _id = id;
        _type = type;
        _usage = usage;
        _status = status;
    }

   /**
    * Empty constructor
    */
    public Material() {

    }

    /**
     * Creates a string to print the information of the material
     * @return The string containing the information of the material
     */
    public String toString() {
        String aux = "Id: " + this._id +
                "\nType: " + this._type;
        if(this._usage==true)
        {
        	aux += "\nUsage: Indoors";
        }
        else
        {
        	aux +="\nUsage: Outdoors";
        }
        aux += "\nStatus: " + this._status;
        return aux;
    }

    /**
     * Gets the ID of the material
     * @return The ID of the material
     */
    public Integer getId() {
        return _id;
    }
    /**
     * Gets the type of the material
     * @return The type of the material
     */
    public MaterialType getType() {
        return _type;
    }
    /**
     * Gets the usage of the material
     * @return The usage of the material
     */
    public boolean getUsage() {
        return _usage;
    }

    /**
     * Gets the status of the material
     * @return The status of the material
     */
    public MaterialStatus getStatus() {
        return _status;
    }

    /**
     * Sets the ID of the material
     * @param id The new ID of the material
     */
    public void setId(Integer id) {
        this._id = id;
    }
    /**
     * Sets the type of the material
     * @param type New type of the material
     */
    public void setType(MaterialType type) {
        this._type = type;
    }
    /**
     * Sets the usage of the material
     * @param usage New usage of the material
     */
    public void setUsage(boolean usage) {
        this._usage = usage;
    }
    /**
     * Sets the status of the material
     * @param status New status of the material
     */
    public void setStatus(MaterialStatus status) {
        this._status = status;
    }
}
