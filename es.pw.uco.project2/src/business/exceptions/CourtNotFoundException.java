package business.exceptions;

public class CourtNotFoundException extends Exception
{
	
	public CourtNotFoundException() {}
	
	public CourtNotFoundException(String err)
	{
		
		super(err);
		
	}

}
