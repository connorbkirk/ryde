package Ryde.persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;




public class RydePersistImpl 
{
	
	
	private Connection con;  
	private DbAccessImplementation dbai; 
	
	
	public RydePersistImpl() throws ClassNotFoundException
	{
		
		DbAccessImplementation dbai = new DbAccessImplementation(); 
		
		con = dbai.connect(); 
		
		
		
	}

	
	public ResultSet returnSingleCar(String userModel) throws SQLException
	{
		ResultSet rs = null; 
		
		
		String query = "select * from cars where model = " + "\"" + userModel + "\""; 
		
		rs = dbai.retrieve(con, query); 
		
		return rs; 
	}
	
	
	public int addCar(String make, String model, int carYear, String color) throws SQLException
	{
		String query = "insert into cars (make, model carYear, color) values (" + "\"" + make + "\", " + "\"" + model + "\", " + carYear + ", " + "\"" + color + "\"" + ")"; 
		
		return dbai.create(con, query); 
	}
	
	public int deleteCar(String name) throws SQLException
	{
		String query = "delete from cars name = " + "\"" + name + "\""; 
		
		return dbai.delete(con, query); 
	}
	
	
	public ResultSet returnCars() throws ClassNotFoundException, SQLException
	{
		//used to return cars in db. 
		

		ResultSet rs = null; 
		
		String query = "select * from cars"; 
		

		
		 
		rs = dbai.retrieve(con, query);
		
		
		return rs; 
	}
	
	public ResultSet returnAllOwners() throws SQLException, ClassNotFoundException
	{
		//used to return all the owners in db. 
		
		String query = "select * from owners"; 
		
		ResultSet rs = null;  

		
		
		rs = dbai.retrieve(con, query); 
		
		
		return rs; 
		
	}
	

	
	public ResultSet returnAllUsers() throws SQLException
	{
		ResultSet rs = null; 
		
		String query  = "select * from owners"; 
		
		rs = dbai.retrieve(con, query); 
		
		return rs; 
		
		
	}
	
	
	
	public boolean verifyLogin(String username, String pass) throws ClassNotFoundException, SQLException
	{
		String query  = "select * from users where (username = " + "\"" + username + "\"" + " AND pswrd = " + "\"" + pass + "\" " + " )" ; 
		
		ResultSet rs = null ; 
		
		
		rs = dbai.retrieve(con, query);
		
		
		if (rs == null)
			return false; 
		else 
			return true; 
		
	}
	
	public ResultSet renterRequest(String toDate, String fromDate, float startPrice, float endPrice, String carType, String carMake) throws SQLException
	{
		
		String yearMaxCommand = "select max(date) from cars"; 
		
		ResultSet rs = null; 
		
		String query = null; 
		
		
		query = "select * from cars where(price between + " + startPrice + " AND " + endPrice + " AND (carType = " + "\"" + carType + "\"" + ") AND (carMake = " + "\"" + carMake + "\"" + ")" ;
		
		
		rs = dbai.retrieve(con, query); 
		
		return rs; 
			
		
	}
	

	
	
	
	public int register(String username, String password, String firstName, String lastName) throws SQLException {
		//this movie adds a row into 'users' with the given username and password
		
		String query = "INSERT INTO users (username, pswrd, firstName, lastName) "+
		"VALUES(\'"+username+"\', \'"+ password + "\', \'" + firstName + "\', \'" + lastName + "\')";
		
		return dbai.create(con, query);
		
	}

	
	
	
	public ResultSet returnSingleUser(String fn, String ln) throws SQLException
	{
		ResultSet rs = null; 
		
		String query = "select * from users where firstname = " + "\"" + fn + "\" " + "AND lastname = " + "\"" + ln + "\""; 
		
		rs = dbai.retrieve(con, query); 
		
		return rs; 
	}
	
	
	
	
	
	
}
