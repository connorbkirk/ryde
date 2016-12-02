package logiclayer;

import persistlayer.UserPersistImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objectlayer.User;

/**
 * This class is the controller class for all the functionalities of users.
 * It directly interacts with the Servlet and user persistent layer classes.
 * @author Connor Kirk
 */
public class UserLogicImpl {
	
	//global movie persistent implementation object
	private UserPersistImpl up;
	
	/**
	 * Default constructor. Creates a new object of UserPersistImpl.
	 */
	public UserLogicImpl(){
		up = new UserPersistImpl();
	}
	
	/**
	 * This method takes a string as a parameter,
	 * hashes it using MD5, and returns the
	 * hashed result.
	 * 
	 * @param original The string to hash.
	 * @return MD5 hash of the string.
	 * @throws NoSuchAlgorithmException Only thrown if MD5 does not
	 * exist in the MessageDigest.
	 */
	private String hash(String original) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		original = new BigInteger(1, md.digest()).toString(16);
		
		return original;
	}
	
	/**
	 * This method takes user information, calls hash to encrypt
	 * the password, and calls the persist layer to create a new
	 * entry in the database.
	 * 
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param email The email of the user.
	 * @param phone The phone number of the user.
	 */
	public void register(String username, String password, String firstName, 
			String lastName, String phone, String email) {
		
		try {
			//hash password with md5
			password = hash(password);
			//call persist to create new entry
			up.register(username, password, firstName, lastName, phone, email);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method takes an input username and password, 
	 * hashes the password, and calls the persist layer
	 * to return true if the username and hashed password
	 * exist as an entry in the users table; false otherwise.
	 * 
	 * @param username The username of the user to validate.
	 * @param password The password of the user to validate.
	 * @return True if username and password match; false otherwise.
	 */
	public boolean validate(String username, String password) {
		
		try {
			password = hash(password);
			return up.validate(username, password);
		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This method calls the UserPersistImpl class to gather
	 * a ResultSet of all users in the database. It then 
	 * processes the ResultSet into a list of User objects.
	 * 
	 * @return A list of User objects.
	 */
	public ArrayList<User> getUsers(){
		ResultSet rs = null; 
		ArrayList<User>returnUsers = new ArrayList<User>(); 
		rs = up.getUsers(); 
		
		try {
			while (rs.next()){
				String lastname = rs.getString("lastName"); ; 
				String firstname = rs.getString("firstName"); ; 
				String username = rs.getString("username");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String password = rs.getString("pswrd"); ; 
				int id = rs.getInt("id");
				
				returnUsers.add(new User(lastname, firstname, username, password, phone, email, id)); 	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnUsers; 
	}
	
	/**
	 * This method calls the UserPersistImpl class to gather
	 * a ResultSet containing an entry of the user with the id
	 * given as a parameter. It then processes the ResultSet 
	 * into a single User object.
	 * 
	 * @param id The id of the User to get.
	 * @return The corresponding User object.
	 */
	public User getSingleUser(int id)
	{
		ResultSet rs = up.getUser(id);
		User returnUser = null; 
		
		try {
			if(rs.next())
			{
				//gather info from resultset
				String lastname = rs.getString("lastName"); 
				String firstname = rs.getString("firstName"); 
				String username = rs.getString("username"); 
				String password = rs.getString("pswrd"); 
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				//create new user object with info from resultset
				returnUser = new User(firstname, lastname, username, password, phone, email, id); 
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
	
	/**
	 * This method takes a string parameter that represents
	 * a username. It then calls the UserPersistImpl object
	 * to get the entry in the users table containing the
	 * corresponding username. Then it will return the
	 * corresponding id.
	 * 
	 * @param username Username of the user.
	 * @return Id of the user.
	 */
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

	/**
	 * This method calls the UserPersistUser to check
	 * if the given username exists in the database.
	 * Returns true if it does; false otherwise.
	 * 
	 * @param username Username to check.
	 * @return True if username exists in the database; false otherwise.
	 */
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
