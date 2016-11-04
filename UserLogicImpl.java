package Ryde.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Ryde.objectlayer.User;
import Ryde.persistlayer.RydePersistImpl;

public class UserLogicImpl 
{
	private RydePersistImpl rpi; 
	
	
	public UserLogicImpl() throws ClassNotFoundException
	{
		rpi = new RydePersistImpl(); 
	}
	
	public ArrayList<User> getUsers() throws SQLException
	{
		ResultSet rs = null; 
		
		ArrayList<User>returnUsers = new ArrayList<User>(); 
		
		rs = rpi.returnUsers(); 
		
		
		while (rs.next())
		{
			String lastname; 
			String firstname; 
			String email; 
			String password; 
			int id; 
			
			lastname = rs.getString("lastname"); 
			firstname = rs.getString("firstname"); 
			email = rs.getString("email"); 
			password = rs.getString("password"); 
			id = rs.getInt("id"); 
			
			
			returnUsers.add(new User(lastname, firstname, email, password, id)); 
			
			
		}
		
		return returnUsers; 
		
		
		
		
		
		
		
	}


	
	
}
