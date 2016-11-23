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
        //ResultSet rs = rpi.verifyDate(int carID, String d1, String d2); 
        
        ResultRest rs = null;
        String dateVar = "["; 
      
      
        rs  =rpi.viewAvailable(); 
      //returns all taken dates. 
    
     if (rs != null)
     {
       //if there are dates taken iterate and create js variable to be parsed. 
      
        while(rs.next())
        {
          String day1 = rs.getString("startDate"); 
          String day2 = rs.getString("endDate"); 


          dateVar = dateVar + "{start:\"" + startDate + "\", end:\"" + endDate + "\"}, ";

          //dateVar = dateVar + startDate + "\", end:\"" + endDate 



        }

        dateVar = dateVar + "]"; 
      
     }
      
      else
        dateVar = "undefined"; 
      
      
      return dateVar; 
  
  
  
 }
