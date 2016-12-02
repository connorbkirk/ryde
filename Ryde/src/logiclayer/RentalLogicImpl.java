package logiclayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import objectlayer.Car;
import objectlayer.Rental;
import persistlayer.CarPersistImpl;
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
  	 * @return returns a string that is formated like a javascript varibale that contains array of objects. 
  	 * @throws SQLException
  	 */

    public String viewUnavailable()
    {
        //ResultSet rs = rpi.verifyDate(int carID, String d1, String d2); 
        
    	Gson gson = new Gson();
    	
    	ArrayList<Gson> tempGson = new ArrayList<Gson>(); 
    	ArrayList<Rental> tempRental = new ArrayList<Rental>(); 
    	
    	
    	
    	
        ResultSet rs = null;
        
        
        String dateVar = null; 
      
      
        rs  =rpi.viewAvailable(); 
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
    
    
    public void addRentalDate(String startDate, String endDate)
    {
    	rpi.addRentalDate(startDate, endDate, 0); 
    	//call function.
    	
  
    }
	
	
}
