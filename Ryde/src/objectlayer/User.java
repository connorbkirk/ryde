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
	 * @param id Id of the user.
	 */
	public User(String firstName, String lastName, String username, String password, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.id = id;
	}
	/**
	 * @return First name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param First name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param Last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Username of the user.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param Username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return Password of the user.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param Password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Id of the user.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param Id of the user.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
