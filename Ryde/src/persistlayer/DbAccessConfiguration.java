package persistlayer;

//this class contains information needed to connect to the database
public abstract class DbAccessConfiguration {
	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";				//name of jdb driver
	static final String CONNECT_URL = "jdbc:mysql://localhost:3306/ryde";	//mysql url
	static final String USER = "root";										//mysql username
	static final String PASS = "weatherbox";								//mysql password
}
