package persistlayer;

import java.sql.Connection;

//This class is the primary persistent (model) layer class. 
//It explicitly communicates with the DbAccessImpl
public class UserPersistImpl {

	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
	
	public void register(String username, String password, String firstName, String lastName) {
		//this movie adds a row into 'users' with the given username and password
		
		String query = "INSERT INTO users (username, pswrd, firstName, lastName) "+
		"VALUES(\'"+username+"\', \'"+ password + "\', \'" + firstName + "\', \'" + lastName + "\')";
		
		db.create(con, query);
		
	}
	
}
