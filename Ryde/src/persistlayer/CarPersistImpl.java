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
	
  	public int addCar(String make, String model, int carYear, String color) throws SQLException
	    {
		String query = "insert into cars (make, model carYear, color) values (" + "\"" + make + "\", " + "\"" + model + "\", " + carYear + ", " + "\"" + color + "\"" + ")";

		return dbai.create(con, query);

		//used to add car in db.
	    }
    
	    public int deleteCar(String name) throws SQLException
	    {
		String query = "delete from cars name = " + "\"" + name + "\"";

		return dbai.delete(con, query);
		//used to delete a single car.
	    }
    
	    public ResultSet returnSingleCar(String userModel) throws SQLException
	    {
		ResultSet rs = null;


		String query = "select * from cars where model = " + "\"" + userModel + "\"";

		rs = dbai.retrieve(con, query);

		//used to return single car.
		//used when selecting single car on user end.

		return rs; 
	    }
	
	
	
	
}
