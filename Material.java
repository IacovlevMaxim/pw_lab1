package assignment1;

public class Material {

	// VARIABLES
	private int id;
	private MaterialType type;
	private boolean usage;
	private MaterialStatus status;
	
	// METHODS
	public Material(){
		this.id = -1;
	}
	
	public Material(int id, MaterialType type, boolean usage, MaterialStatus status)
	{
		this.id = id;
		this.usage = usage;
		this.type=type;
		this.status=status;
	}
	// GET METHODS
	public int getId()
	{
		return this.id;
	}
	
	public boolean getUsage()
	{
		return this.usage;
	}
	
	public MaterialType getType()
	{
		return this.type;
	}
	
	public MaterialStatus getStatus()
	{
		return this.status;
	}
	
	// SET METHODS
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setUsage(boolean usage)
	{
		this.usage = usage;
	}

	public void setType(MaterialType type)
	{
		this.type = type;
	}

	public void setStatus(MaterialStatus status)
	{
		this.status = status;
	}
	
	// TOSTTRING METHOD
	public String toString() {
		String materialInfo;
		materialInfo = "\tMaterial ID: " + this.id + "\n";
		materialInfo += "\tMaterial type: " + this.type + "\n";	
		if (this.usage)
		{
			materialInfo += "\tMaterial usage: Indoors\n";
		}
		else
		{
			materialInfo += "\tMaterial usage: Outdoors\n";
		}
		materialInfo += "\tMaterial status: " + this.status + "\n";
		return materialInfo;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String info;
		MaterialType aux2 = MaterialType.BALL;
		MaterialStatus aux3 = MaterialStatus.AVAILABLE;
		Material aux1 = new Material(10, aux2, true, aux3);
		info = aux1.toString();
		System.out.println(info);
	}

}
