package persistlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl implements DbAccessInterface {

	public Connection connect() {
		//this method loads the jdbc driver and opens a connection to the mysql database
		
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

	public ResultSet retrieve(Connection con, String query) {
		//this method runs a given SQL query and returns the results as a resultSet
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

	public int create(Connection con, String query) {
		//this method runs a given SQL query that creates a row in the database
		//it returns the number of rows affected
		
		try {
			//create statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(Connection con, String query) {
		//this method runs a given SQL query that updates a row in the database
		//it returns the number of rows affected
		
		try {
			//create a statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(Connection con, String query) {
		//this method runs a given SQL query that deletes a row in the database
		//it returns the number of rows affected
		
		try {
			//create a statement from connection
			Statement statement = con.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void disconnect(Connection con) {
		//this method closes the connection to the database
		
		try {
			if (con != null)
				con.close();
		} catch(SQLException e) {
				e.printStackTrace();
		} 
	}

}
