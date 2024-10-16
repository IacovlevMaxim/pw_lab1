package classes;

import enums.MaterialType;
import enums.MaterialStatus;

public class Material {
    private Integer _id;
    private MaterialType _type;
    private boolean _usage;
    private MaterialStatus _status;

    // Constructor with parameters
    public Material(Integer id, MaterialType type, boolean usage, MaterialStatus status) {
        _id = id;
        _type = type;
        _usage = usage;
        _status = status;
    }

    // Default constructor
    public Material() {

    }

    @Override
    public String toString() {
        return "Material\nId: " + this._id +
                "\nType: " + this._type +
                "\nUsage: " + this._usage +
                "\nStatus: " + this._status;
    }

    // Getters
    public Integer getId() {
        return _id;
    }

    public MaterialType getType() {
        return _type;
    }

    public boolean getUsage() {
        return _usage;
    }

    public MaterialStatus getStatus() {
        return _status;
    }

    // Setters
    public void setId(Integer id) {
        this._id = id;
    }

    public void setType(MaterialType type) {
        this._type = type;
    }

    public void setUsage(boolean usage) {
        this._usage = usage;
    }

    public void setStatus(MaterialStatus status) {
        this._status = status;
    }
}
