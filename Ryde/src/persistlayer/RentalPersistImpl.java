package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * This class is the persistent layer class for the Rental object. 
 * It explicitly communicates with the DbAccessImpl.
 * 
 * @author Connor Kirk
 *
 */
public class RentalPersistImpl{
	
	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
	
	/**
	 * 
	 * This method creates and executes a SQL statement that 
	 * creates an entry in the database with the given parameters.
	 * 
	 * @param d1 Start date in the format yyyy-MM-dd.
	 * @param d2 End date in the format yyyy-MM-dd.
	 * @param carId Id of the car being rented.
	 * @param renterId Id of the user renting.
	 */
	public void addRentalDate(String d1, String d2, int carId, int renterId){
		
	    String query = "INSERT INTO rental_dates (carID, startDate, endDate, renterID) VALUES"
	    		+" ( " + carId + ", \'" + d1 + "\', \'" + d2 + "\', \'" + renterId + "\')"; 
	    
	    db.create(con, query); 
	}
	  
	/**
	 * This method creates and executes a query that 
	 * gathers all entries for a given car ID. The method
	 * then returns the results as a result set.
	 * 
	 * @param carId Id of the car to search.
	 * @return ResultSet containing all entries containing the car ID.
	 */
	public ResultSet viewUnavailable(int carId){
	    String query = "SELECT * from rental_dates where carID=" + carId; 
	    
	    return db.retrieve(con, query); 
	  
	}
	
	/**
	 * This method creates and executes a query that 
	 * gathers all entries for a given user ID. The 
	 * method then returns the results as a result set.
	 * 
	 * @param userId Id of the user to search.
	 * @return ResultSet containing all entries with the user ID.
	 */
	public ResultSet viewUserRented(int userId){
		String query = "SELECT * FROM rental_dates where renterID=" + userId;
		
		return db.retrieve(con, query);
	}
	
	/**
	 * This method creates and executes a query
	 * that removes an entry from the database with
	 * the given id.
	 * 
	 * @param id Id of the rental to delete.
	 */
	public void deleteRental(int id){
		String query = "DELETE FROM rental_dates WHERE id="+id;
		db.delete(con, query);
	}

	/**
	 * This method creates and executes a query
	 * that gathers an entry from the database with
	 * the given id. The resulting entry is returned
	 * as a ResultSet.
	 * 
	 * @param id Id of the rental to get.
	 * @return ResultSet containing the entry with the given Id.
	 */
	public ResultSet getRental(int id) {
		String query = "SELECT * FROM rental_dates where id="+id;
		return db.retrieve(con, query);
	}
	
}

