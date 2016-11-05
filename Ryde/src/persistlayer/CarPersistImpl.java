package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

//This class is the primary persistent (model) layer class. 
//It explicitly communicates with the DbAccessImpl
public class CarPersistImpl {

	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
	
	public ResultSet getCars() 
	{
		ResultSet rs;
		String query = "SELECT * FROM cars";
		rs = db.retrieve(con, query);
		return rs;
	}
	
  	public int addCar(String make, String model, int carYear, String color, int price, String description) throws SQLException
	    {
		String query = "insert into cars (make, model carYear, color) values (" + "\"" + make + "\", " + "\"" + model + "\", " + carYear + ", " + "\"" + color + "\", " + price + ", " + "\""+ description + "\"" + ")";

		return db.create(con, query);

		//used to add car in db.
	    }
    
	    public int deleteCar(String name) throws SQLException
	    {
		String query = "delete from cars name = " + "\"" + name + "\"";

		return db.delete(con, query);
		//used to delete a single car.
	    }
    
	    public ResultSet returnSingleCar(String userMake, String userModel) throws SQLException
	    {
		ResultSet rs = null;


		String query = "select * from cars where make = " + "\"" + userMake + "\", " + "AND model= " + "\"" + userModel + "\"";

		rs = db.retrieve(con, query);

		//used to return single car.
		//used when selecting single car on user end.

		return rs; 
	    }
	
	public ResultSet checkRental (String startDate, String endDate)
	{
		ResultSet rs = null; 
		
		String query = "select * from rental_dates where startDate = "  + "\"" + startDate + "\", AND endDate = " + "\"" + endDate + "\""; 
		
		rs = db.retrieve(con, query); 
		
		return rs; 
		
		//used to check rental. 
		//if rental already exsists the rs will not be null, and thus user cannot make a rental for that date. If null can make rental. 
	}
	
	
	
	
}
