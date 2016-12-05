package objectlayer;

/**
 * This class represents the model of a user object.
 * 
 * @author Connor Kirk
 *
 */
public class User {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phone;
	private String email;
	private int id;
	
	/**
	 * This is the constructor for the object.
	 * It requires the first name, last name,
	 * username, password, and id.
	 * 
	 * @param firstName First name of the user.
	 * @param lastName Last name of the user.
	 * @param username Username of the user.
	 * @param password Password of the user.
	 * @param phone Phone number of the user.
	 * @param email Email address of the user.
	 * @param id Id of the user.
	 */
	public User(String firstName, String lastName, String username, String password, String phone, 
			String email, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.id = id;
	}
	
	/**
	 * This method is the getter for phone.
	 * @return Phone number of the user.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method is the setter for phone.
	 * @param phone Phone number of the user.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method is the getter for email.
	 * @return Email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method is the setter for email.
	 * @param email Email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method is the getter for firstName.
	 * @return First name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * This method is the setter for firstName.
	 * @param firstName First name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * This method is the getter for lastName.
	 * @return Last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * This method is the setter for lastName.
	 * @param lastName Last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * This method id the getter for username.
	 * @return Username of the user.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * This method is the setter for username.
	 * @param username Username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * This method is the getter for password.
	 * @return Password of the user.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This method is the setter for password.
	 * @param password Password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * This method is the getter for id.
	 * @return Id of the user.
	 */
	public int getId() {
		return id;
	}
	/**
	 * This method is the setter for id.
	 * @param id Id of the user.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
