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
  
  public RentalLogicImpl()
  {
    rpi = new RentalPersistImpl(); 
  
  }


    public String viewUnavailable() throws SQLException
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
      
        while(rs.next())
        {
          String day1 = rs.getString("startDate"); 
          String day2 = rs.getString("endDate"); 
          
          tempRental.add(new Rental(day1, day2)); 
        }

        
        dateVar = gson.toJson(tempRental); 
        //converts list of rentals to json format. 
        
      
     }
      
      else
        dateVar = "undefined"; 
      
      
      return dateVar; 
  
  
  
 }
}
