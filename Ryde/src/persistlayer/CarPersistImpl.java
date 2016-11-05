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
	
}
