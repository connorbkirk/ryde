package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public boolean validate(String username, String password) throws SQLException{
		//password must be hashed before calling
		
		String query = "SELECT * FROM users WHERE username = \'" + username +"\'"
				+"AND pswrd=\'"+password+"\'";
		
		ResultSet rs = db.retrieve(con, query);
		return rs.next();
	}

	public ResultSet getUsers() {
		ResultSet rs = null; 
		String query  = "SELECT * FROM users"; 
		rs = db.retrieve(con, query); 
		
		return rs; 
	}

	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE id = \'" + id + "\'";
		rs = db.retrieve(con, query);
		return rs;
	}

	public ResultSet getIdFromUsername(String username) {
		String query = "SELECT id FROM users WHERE username = \'" + username + "\'";
		return db.retrieve(con, query);
	}

	
	
}
