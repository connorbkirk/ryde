package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

//This class is the primary persistent (model) layer class. 
//It explicitly communicates with the DbAccessImpl
public class CarPersistImpl {

	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
	
	public ResultSet getCars() {
		ResultSet rs;
		String query = "SELECT * FROM cars";
		rs = db.retrieve(con, query);
		return rs;
	}

	public ResultSet getTypes() {
		String query = "SELECT carType FROM cars";
		return db.retrieve(con, query);
	}

	public ResultSet getModels() {
		String query = "SELECT model FROM cars";
		return db.retrieve(con, query);
	}

	public ResultSet getMakes() {
		String query = "SELECT make FROM cars";
		return db.retrieve(con, query);
	}

	public ResultSet getCar(String id) {
		String query = "SELECT * FROM cars where id = \'" + id + "\'";
		return db.retrieve(con, query);
	}
	
	public boolean verifyData(String d1, String d2)
	{
		ResultSet rs = null; 
		
		String query = "select * from rental_dates where startDate = " + d1 + " AND endDate = " + d2; 
		
		rs = db.retrieve(con, query); 
		
		if (rs == null)
			return false; 
		else 
			return true; 
		
	}

	
}
