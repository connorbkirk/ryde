package objectlayer;



public class Rental 
{
	
	private String startDate; 
	private String endDate; 
	private int carID; 
	
	
	
	public Rental(int inputID, String d1, String d2)
	{
		carID = inputID; 
		startDate = d1; 
		endDate = d2;
	}
	//most basic object of rental. 
	
	public Rental(String d1, String d2)
	{ 
		startDate = d1; 
		endDate = d2;
	}
	//rental object sans the car ID. 
	
	
	


}
