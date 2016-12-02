package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the persistent layer class for the Car object. 
 * It explicitly communicates with the DbAccessImpl
 * 
 * @author Connor Kirk
 *
 */
public class UserPersistImpl {

	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();
	
	/**
	 * This method creates and executes a query that
	 * adds a row into the users table with the given 
	 * values.
	 * 
	 * @param username Username to register.
	 * @param password Password to register.
	 * @param firstName First name to register.
	 * @param lastName Last name to register.
	 */
	
	public void register(String username, String password, String firstName, String lastName) {
		String query = "INSERT INTO users (username, pswrd, firstName, lastName) "+
		"VALUES(\'"+username+"\', \'"+ password + "\', \'" + firstName + "\', \'" + lastName + "\')";
		
		db.create(con, query);
		
	}
	
	/**
	 * This method checks whether or not the given
	 * username and password combination exist are 
	 * valid (exist in database). The password must
	 * be hashed using MD5 before calling.
	 * 
	 * @param username Username to validate.
	 * @param password Password to validate.
	 * @return True if username matches password; false otherwise.
	 * @throws SQLException If the ResultSet is null (query error).
	 */
	public boolean validate(String username, String password) throws SQLException {
		//password must be hashed before calling
		
		String query = "SELECT * FROM users WHERE username = \'" + username +"\'"
				+"AND pswrd=\'"+password+"\'";
		
		ResultSet rs = db.retrieve(con, query);
		return rs.next();
	}

	/**
	 * This method creates and executes an SQL
	 * query that gathers all entries in the users
	 * table and returns a ResultSet of the results.
	 * 
	 * @return ResultSet containing all users.
	 */
	public ResultSet getUsers() {
		ResultSet rs = null; 
		String query  = "SELECT * FROM users"; 
		rs = db.retrieve(con, query); 
		
		return rs; 
	}

	/**
	 * This method creates and executes a query
	 * that gathers the entry in the users table
	 * with the given id. Returns the results in
	 * a ResultSet.
	 * 
	 * @param id Id of user to get.
	 * @return Entry containing given id.
	 */
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE id = \'" + id + "\'";
		rs = db.retrieve(con, query);
		return rs;
	}

	/**
	 * This method creates and executes a query
	 * that returns the id of a user given the
	 * username. The result is returned in a 
	 * ResultSet.
	 * 
	 * @param username Username of user.
	 * @return Id of user.
	 */
	public ResultSet getIdFromUsername(String username) {
		String query = "SELECT id FROM users WHERE username = \'" + username + "\'";
		return db.retrieve(con, query);
	}

	
	
}
