package logiclayer;

import persistlayer.UserPersistImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//This class is the controller class for all the functionalities of cars.
//It directly interacts with the Servlet and car persistent layer classes.
public class UserLogicImpl {
	
	//global movie persistent implementation object
	UserPersistImpl up = new UserPersistImpl();
	
	public void register(String username, String password, String firstName, String lastName) throws NoSuchAlgorithmException{
		
		//hash password with md5
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		password = new BigInteger(1, md.digest()).toString(16);
		
		//call persistlayer to update database
		up.register(username, password, firstName, lastName);
		
	}
}
