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

import objectlayer.Car;
import persistlayer.CarPersistImpl;


public class RentalLogicImpl()
{
  private RentalPersistImpl rpi; 
  
  public RentalLogicImpl()
  {
    rpi = new RentalPersistImpl(); 
  
  }


    public String viewUnavailable(int carID, String d1, String d2)
    {
        ResultSet rs = rpi.verifyDate(int carID, String d1, String d2); 
        
        ArrayList<RentalDates>rd = new ArrayList<RentalDates>(); 
        
        String jsVar = "var invalidDates = ["; 
        
        while (rs.next())
        {
        
          String day1 = rs.getString("startDate"); 
          String day2 = rs.getString("endDate"); 
          
          ijsVar = jsVar + "{start: \" + day1 + "\", end: \"" + day2 + "\"},"; 
          
          //rd.add(new RentalDates(startDate, endDate)); 
        
        }
        
        jsVar = jsVar + "];"; 
        
        return jsVar; 
        
    }
  
  
  
 }
