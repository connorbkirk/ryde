/**
 * 
 */
package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * This is the interface for accessing the database. 
 * 
 * @author connor
 *
 */
public interface DbAccessInterface {
	/**
	 * This method loads the JDBC driver and opens a connection to the MySQL database.
	 * 
	 * @return Connection object to the MySQL database
	 */
	public Connection connect();
	/**
	 * This method runs a given SQL query and returns 
	 * the results as a ResultSet.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return ResultSet of executed query.
	 */
	public ResultSet retrieve (Connection con, String query);
	/**
	 * This method runs a given SQL query that creates rows in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int create (Connection con, String query);
	/**
	 * This method runs a given SQL query that updates rows in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int update (Connection con, String query);
	/**
	 * This method runs a given SQL query that deletes a row in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int delete (Connection con, String query);
	/**
	 * This method disconnects the given connection.
	 * 
	 * @param con Connection to disconnect.
	 */
	public void disconnect(Connection con);
}
