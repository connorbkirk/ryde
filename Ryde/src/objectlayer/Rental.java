package objectlayer;



/**
 * 
 * This class is used to create and return rental objects. 
 * Interacts with all layers as allowed by MVC. 
 * @author SahiComputer
 *
 */
public class Rental 
{
	
	private String startDate; 
	private String endDate; 
	private int carID; 
	
	/**
	 * Constructor that creates rental object. 
	 * Rarely if ever used, only difference between one below is inclusion of id from mysql. 
	 * 
	 * @param inputID rental object id in server. 
	 * @param d1 start date. 
	 * @param d2 end date. 
	 */
	
	public Rental(int inputID, String d1, String d2)
	{
		carID = inputID; 
		startDate = d1; 
		endDate = d2;
	}
	//most basic object of rental. 
	
	
	
	
	
	/**
	 * Constructor for date object that takes start and end date as parameter. 
	 * Assigns parameters to instance variables. 
	 * start and end date info from server. 
	 * 
	 * @param d1 the start date. 
	 * @param d2 the end date.
	 */
	public Rental(String d1, String d2)
	{ 
		startDate = d1; 
		endDate = d2;
	}
	//rental object sans the car ID. 
	
	
	
	
	/**
	 * @return the start date. 
	 */
	public String getStartDate()
	{
		return startDate; 
	}
	
	
	/**
	 * 
	 * @return the end date. 
	 */
	public String getEndDate()
	{
		return endDate; 
	}
	


}
