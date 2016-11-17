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
  
  public ResultSet viewTaken(int carID)
  {
    String query = "SELECT * from rental_dates where carID = "+carID; 
    
    return db.retrieve(con, query); 
  
  }
	  
public ResultSet verifyDate(int carID, String d1, String d2)
{
	
	//used to check if date is in the DB for cetain car.If it is this means that we cannot rent for that time frame for that car. 
	String query = "SELECT * FROM rental_dates WHERE carID = " + carID + "AND WHERE start_date=  " + d1 + " AND WHERE end_date= " + d2; 
	
	return db.retrieve(con, query); 
}
  
  
  
  }

