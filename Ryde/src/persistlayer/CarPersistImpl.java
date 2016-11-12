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

	public ResultSet getCarsFromUser(int ownerId) {
		String query = "SELECT * FROM cars where ownerID = \'" + ownerId + "\'";
		return db.retrieve(con, query);
	}

	//returns the car id after adding it to the db
	public ResultSet addCar(int ownerId, String make, String model, String year, 
			String color, String price, String description, String carType) {
		String query = "INSERT INTO cars (carType, make, model, carYear, color, ownerId, price, description) " +
			"VALUES (\'"+carType+"\', \'"+make+"\', \'"+model+"\', "+year+", "
			+"\'" + color + "\', " + ownerId + ", " + price + ", \'" + description + "\')";
		db.create(con, query);
		
		query = "SELECT LAST_INSERT_ID();";
		return db.retrieve(con, query);
	}

	public void editCar(String id, String make, String model, String year, String color, String price,
			String description, String carType) {
		String query = "UPDATE cars "
				+ "SET make=\'"+make+"\', model=\'"+model+"\', carYear=\'"+year+"\', color=\'"+color +"\', "
				+ "price=\'"+price+"\', description=\'"+description+"\', carType=\'"+carType+"\' "
				+ "WHERE id=\'"+id+"\'";
		db.update(con, query);
		
	}

	public ResultSet getOwnerId(String id) {
		// TODO Auto-generated method stub
		String query = "SELECT ownerID FROM cars WHERE id=\'"+id+"\'";
		return db.retrieve(con, query);
	}

	public void deleteCar(String id) {
		String query = "DELETE FROM cars WHERE id=\'"+id+"\'";
		db.delete(con, query);
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
