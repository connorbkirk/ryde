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
  
  public ResultSet viewAllTaken()
  {
	 //returns all general taken rental dates. 
	  
    String query = "SELECT * from rental_dates"; 
	 
    
    return db.retrieve(con, query); 
  
  }
	  
public ResultSet viewTakenSpecefic(int cardID)
{
	//returns taken rental dates for certain car. 
	
	String query = "SELECT * from rental_dates where carID = " + cardID; 
	
	return db.retrieve(con, query); 
	
}
	  
	  
public ResultSet viewAvailableSpecefic(int carID, String d1, String d2)
{
	//returns all dates not between certain date frame the user has. 
	
	String query = "SELECT carID from rental_dates WHERE NOT BETWEEN " + d1 + " AND " + d2; 
	
	return db.retrieve(con, query); 
	
}
	  
public ResultSet verifyDate(int carID, String d1, String d2)
{
	
	//used to check if date is in the DB for cetain car.If it is this means that we cannot rent for that time frame for that car. 
	String query = "SELECT * FROM rental_dates WHERE carID = " + cardID + "AND WHERE start_date=  " + d1 + " AND WHERE end_date= " + d2; 
	
	return db.retrieve(con, query); 
}
  
  
  }

