package persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is the implementation of the interface DbAccessInterface
 * 
 * @author Connor Kirk
 *
 */
public class DbAccessImpl implements DbAccessInterface {

	/**
	 * This method loads the JDBC driver and opens a connection to the MySQL database.
	 * 
	 * @return Connection object to the MySQL database
	 */
	public Connection connect() {
		//create connection
		Connection conn = null;
		try{
			//connect to mysql
			Class.forName(DbAccessConfiguration.DRIVER_NAME);
			conn = DriverManager.getConnection(DbAccessConfiguration.CONNECT_URL, 
					DbAccessConfiguration.USER, DbAccessConfiguration.PASS);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return conn;
	}

	/**
	 * This method runs a given SQL query and returns 
	 * the results as a ResultSet.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return ResultSet of executed query.
	 */
	public ResultSet retrieve(Connection con, String query) {
		ResultSet rs = null;
		try{
			//create statement from connection and resultset from query
			Statement statement = con.createStatement();
			rs = statement.executeQuery(query);
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
			return rs;
		}
	}

	/**
	 * This method runs a given SQL query that creates rows in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int create(Connection con, String query) {
		try {
			//create statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * This method runs a given SQL query that updates rows in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int update(Connection con, String query) {
		try {
			//create a statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * This method runs a given SQL query that deletes a row in the database.
	 * It returns the number of rows affected as an int.
	 * 
	 * @param con Connection object.
	 * @param query Query to execute.
	 * @return The number of rows affected by query.
	 */
	public int delete(Connection con, String query) {
		
		try {
			//create a statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * This method closes the given connection to the database.
	 * 
	 * @param con Connection to disconnect.
	 */
	public void disconnect(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch(SQLException e) {
				e.printStackTrace();
		} 
	}

}
