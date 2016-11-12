package logiclayer;

import persistlayer.UserPersistImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objectlayer.User;

//This class is the controller class for all the functionalities of cars.
//It directly interacts with the Servlet and car persistent layer classes.
public class UserLogicImpl {
	
	//global movie persistent implementation object
	private UserPersistImpl up;
	
	public UserLogicImpl(){
		up = new UserPersistImpl();
	}
	
	private String hash(String original) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		original = new BigInteger(1, md.digest()).toString(16);
		
		return original;
	}
	
	public void register(String username, String password, String firstName, String lastName) {
		
		//hash password with md5
		try {
			password = hash(password);
			up.register(username, password, firstName, lastName);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//call persistlayer to update database
	}

	public boolean validate(String username, String password) throws NoSuchAlgorithmException, SQLException {
		// TODO Auto-generated method stub
		
		password = hash(password);
		return up.validate(username, password);
	}
	
	public ArrayList<User> getUsers() throws SQLException{
		ResultSet rs = null; 
		ArrayList<User>returnUsers = new ArrayList<User>(); 
		rs = up.getUsers(); 
		
		while (rs.next()){
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
	
	public User getSingleUser(int id)
	{
		ResultSet rs = up.getUser(id);
		User returnUser = null; 
		
		try {
			if(rs.next())
			{
				String lastname = rs.getString("lastname"); 
				String firstname = rs.getString("firstname"); 
				String username = rs.getString("username"); 
				String password = rs.getString("pswrd"); 
				
				returnUser = new User(firstname, lastname, username, password, id); 
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnUser; 
	}
	
	public int getIdFromUsername(String username){
		ResultSet rs = up.getIdFromUsername(username);
		try {
			if(rs.next())
				return rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public boolean usernameExists(String username) {
		ResultSet rs = up.getIdFromUsername(username);
		try {
			return(rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
