package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import objectlayer.Rental;
import persistlayer.RentalPersistImpl;


public class RentalLogicImpl
{
  private RentalPersistImpl rpi; 
  
	
  /**
   * constructor that creates a persist layer object.
   */
  public RentalLogicImpl()
  {
    rpi = new RentalPersistImpl(); 
  
  }

	
	

  	/**
  	 * Function used to display all taken dates. 
  	 * Started by JS calling servlet which calls this and then calls persit function. 
  	 * @param carId 
  	 * @return returns a string that is formated like a javascript varibale that contains array of objects. 
  	 * @throws SQLException
  	 */

    public String viewUnavailable(int carId)
    {
    	//ResultSet rs = rpi.verifyDate(int carID, String d1, String d2); 
        
    	Gson gson = new Gson();
    	
    	ArrayList<Gson> tempGson = new ArrayList<Gson>(); 
    	ArrayList<Rental> tempRental = new ArrayList<Rental>(); 
    	
        ResultSet rs = null;
        
        String dateVar = null; 
      
      
        rs = rpi.viewAvailable(carId); 
        //returns all taken dates. 
    
        if (rs != null)
        {
        	//if there are dates taken iterate and create js variable to be parsed. 
      
        	try {
				while(rs.next())
				{
				  String day1 = rs.getString("startDate"); 
				  String day2 = rs.getString("endDate"); 
				  
				  tempRental.add(new Rental(day1, day2)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	        dateVar = gson.toJson(tempRental); 
	        //converts list of rentals to json format. 
        }
        else
	        dateVar = "undefined"; 
	    return dateVar; 
    }
	
	
	
	  
    /**
     * This function takes the start and end date from the user input and passes them to addRental function. 
     * The addRentalDate in persist then adds the rental date in DB. 
     * @param startDate start date of rental from user input html.
     * @param endDate end date of rental from user input html.
     */
    
    
    public void addRentalDate(String startDate, String endDate, int id)
    {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
			rpi.addRentalDate(df.format(start), df.format(end), id); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//call function.
    	
  
    }
	
	
	public ArrayList<Car> getRentedCars(int owner) throws SQLException
    {
    	Gson gson = new Gson();
    	
    	ArrayList<Gson> tempGson = new ArrayList<Gson>(); 
    	ArrayList<Rental> tempRental = new ArrayList<Rental>(); 
    	
    	ResultSet rs; 
    	
    	
    	CarLogicImpl cli = new CarLogicImpl(); 
    	CarPersistImpl cpi = new CarPersistImpl(); 
    	
    	
    	ArrayList<Car>resultCars = new ArrayList<Car>(); 
    	ArrayList<Rental> resultRental = new ArrayList<Rental>(); 
    	ArrayList<Car> bookedCars = new ArrayList<Car>(); 
    	
    	
    	
	    	resultCars = (ArrayList<Car>) cli.getCarsFromUser(owner);
	    	rs = rpi.viewAvailable(); 
	    	
	        if (rs != null)
	        {
	          //if there are dates taken iterate and create js variable to be parsed. 
	         
	           while(rs.next())
	           {
	             String day1 = rs.getString("startDate"); 
	             String day2 = rs.getString("endDate"); 
	             int carId = rs.getInt("carID"); 
	             
	             tempRental.add(new Rental(carId, day1, day2)); 
	           }
	        }
	        
	        
	        for (int i = 0; i<resultCars.size(); i++)
	        {
	        	Car tempCar = resultCars.get(i); 
	        	
	        	for (int j = 0; j<tempRental.size(); j++)
	        	{
	        		Rental tempRent = tempRental.get(i);
	        		
	        		if ( tempCar.getId() == tempRent.getCarID())
	        		{
	        			bookedCars.add(tempCar);
	        		}
	        	}
	        	
	        }

	    	
	    	return bookedCars;

    		
    }
    
	
	
}
