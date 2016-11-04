/**
 * 
 */
package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

//this is the interface for accessing the database. 
public interface DbAccessInterface {
	public Connection connect();
	public ResultSet retrieve (Connection con, String query);
	public int create (Connection con, String query);
	public int update (Connection con, String query);
	public int delete (Connection con, String query);
	public void disconnect(Connection con);
}
