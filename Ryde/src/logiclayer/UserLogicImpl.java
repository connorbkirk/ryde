package logiclayer;

import persistlayer.UserPersistImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

//This class is the controller class for all the functionalities of cars.
//It directly interacts with the Servlet and car persistent layer classes.
public class UserLogicImpl {
	
	//global movie persistent implementation object
	UserPersistImpl up = new UserPersistImpl();
	
	String hash(String original) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		original = new BigInteger(1, md.digest()).toString(16);
		
		return original;
	}
	
	public void register(String username, String password, String firstName, String lastName) throws NoSuchAlgorithmException {
		
		//hash password with md5
		password = hash(password);
		
		//call persistlayer to update database
		up.register(username, password, firstName, lastName);
		
	}

	public boolean validate(String username, String password) throws NoSuchAlgorithmException, SQLException {
		// TODO Auto-generated method stub
		
		password = hash(password);
		return up.validate(username, password);
	}
}
