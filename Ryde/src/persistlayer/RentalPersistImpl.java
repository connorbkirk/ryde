package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
  
  
  public class RentalPersistImpl
  {
	
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
  
  public int addRentalDate(String d1, String d2, int id)
  {
    String query = "INSERT INTO rental_dates (carID, startDate, endDate) VALUES"
    		+" ( " + id + ", \'" + d1 + "\', " + "\'" + d2 + "\')"; 
    
    return db.create(con, query); 
  }
  
  public ResultSet viewAvailable()
  {
    String query = "SELECT * from rental_dates"; 
    
    return db.retrieve(con, query); 
  
  }
  
  
  }

