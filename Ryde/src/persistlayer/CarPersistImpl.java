package persistlayer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the persistent layer class for the Car object.
 * It explicitly communicates with the DbAccessImpl
 *
 * @author Connor Kirk
 *
 */
public class CarPersistImpl {

	//global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();

	/**
	 * This method creates a SQL query that
	 * gathers all cars from the database
	 * and returns them as a ResultSet.
	 *
	 * @return ResultSet of all cars.
	 */
	public ResultSet getCars() {
		ResultSet rs;
		String query = "SELECT * FROM cars";
		rs = db.retrieve(con, query);
		return rs;
	}

	/**
	 * Finds cars with specified attributes
	 * @param make a type of car manufacturer.
	 * @param carType the type of the car.
	 *
	 */
	public ResultSet getCarsWithParams(String carType, String make ){
		if(make!=null)
			make = make.toUpperCase();
		if(carType!=null)
			carType = carType.toUpperCase();
		ResultSet rs;
		String query = "SELECT * FROM cars WHERE ";
		if(make != null){
			query += "make = \'" + make + "\' ";
			if(carType!=null)
				query += " AND carType = \'" +carType+"\'";
		}
		else if(carType!=null)
			query += "carType = \'"+carType+"\'";

		/*if(model != null) query.append("model = "+model);
		if(carYear != null) query.append("carYear = "+carYear);
		if(color != null) query.append("color = "+color);
		if(price != null) query.append("price = "+price); */

		rs = db.retrieve(con, query);
		return rs;
	}

	/**
	 * This method creates a SQL query that
	 * gathers all car types from the database
	 * and returns them as a ResultSet.
	 *
	 * @return ResultSet of all car types.
	 */
	public ResultSet getTypes() {
		String query = "SELECT carType FROM cars";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query that
	 * gathers all car models from the database
	 * and returns them as a ResultSet.
	 *
	 * @return ResultSet of all car models.
	 */
	public ResultSet getModels() {
		String query = "SELECT model FROM cars";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query that
	 * gathers all car makes from the database
	 * and returns them as a ResultSet.
	 *
	 * @return ResultSet of all car makes.
	 */
	public ResultSet getMakes() {
		String query = "SELECT make FROM cars";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query that
	 * gather an entry containing the given
	 * id and returns it as a ResultSet.
	 *
	 * @param Id of car.
	 * @return ResultSet car with given id.
	 */
	public ResultSet getCar(int id) {
		String query = "SELECT * FROM cars where id = \'" + id + "\'";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query that
	 * gathers all cars from a given user from
	 * the database and returns them as a ResultSet.
	 *
	 * @param Id of owner.
	 * @return ResultSet of all cars belonging to given user.
	 */
	public ResultSet getCarsFromUser(int ownerId) {
		String query = "SELECT * FROM cars where ownerID = \'" + ownerId + "\'";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query to create
	 * an entry in the car table of the database
	 * with the given information. It then returns
	 * a ResultSet containing the car id.
	 *
	 * @param ownerId Id of owner.
	 * @param make Make of car.
	 * @param model Model of car.
	 * @param year Year of car.
	 * @param color Color of car.
	 * @param price Price per day of car.
	 * @param description Description of car.
	 * @param carType Body type of car.
	 * @return ResultSet containing id of car.
	 */
	public ResultSet addCar(int ownerId, String make, String model, String year,
			String color, String price, String description, String carType) {
		make = make.toUpperCase();
		model = model.toUpperCase();
		color = color.toUpperCase();
		carType = carType.toUpperCase();
		String query = "INSERT INTO cars (carType, make, model, carYear, color, ownerId, price, description) " +
			"VALUES (\'"+carType+"\', \'"+make+"\', \'"+model+"\', "+year+", "
			+"\'" + color + "\', " + ownerId + ", " + price + ", \'" + description + "\')";
		db.create(con, query);

		query = "SELECT LAST_INSERT_ID();";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates a SQL query that
	 * updates the row in the database with the
	 * given id with the given information.
	 *
	 * @param id Id of car.
	 * @param make Make of car.
	 * @param model Model of car.
	 * @param year Year of car.
	 * @param color Color of car.
	 * @param price Price of car.
	 * @param description Description of car.
	 * @param carType Body type of car.
	 */
	public void editCar(int id, String make, String model, String year, String color, String price,
			String description, String carType) {
		make = make.toUpperCase();
		model = model.toUpperCase();
		color = color.toUpperCase();
		carType = carType.toUpperCase();
		String query = "UPDATE cars "
				+ "SET make=\'"+make+"\', model=\'"+model+"\', carYear=\'"+year+"\', color=\'"+color +"\', "
				+ "price=\'"+price+"\', description=\'"+description+"\', carType=\'"+carType+"\' "
				+ "WHERE id=\'"+id+"\'";
		db.update(con, query);

	}

	/**
	 * This method creates and runs a SQL query that
	 * gets the id of the owner given the id of a car.
	 * It returns the owner id as a ResultSet.
	 *
	 * @param id Id of car.
	 * @return Id of owner.
	 */
	public ResultSet getOwnerId(int id) {
		String query = "SELECT ownerID FROM cars WHERE id=\'"+id+"\'";
		return db.retrieve(con, query);
	}

	/**
	 * This method creates and runs a SQL query
	 * that removes an entry from the car table
	 * with the given id.
	 *
	 * @param id Id of car.
	 */
	public void deleteCar(int id) {
		String query = "DELETE FROM cars WHERE id=\'"+id+"\'";
		db.delete(con, query);
	}

	/**
	 * This method creates and runs a SQL query
	 * that verifies rental dates. Returns true
	 * if they are valid; false otherwise.
	 *
	 * @param d1 From date.
	 * @param d2 To date.
	 * @return Return true if dates are valid; false otherwise.
	 */
	public boolean verifyData(String d1, String d2)
	{
		ResultSet rs = null;

		String query = "select * from rental_dates where startDate = " + d1 + " AND endDate = " + d2;

		rs = db.retrieve(con, query);

		if (rs == null)
			return false;
		else
			return true;
	}

	/**
	 * This method creates and runs a SQL query
	 * that inserts an image into the image table.
	 * This method then returns the entry as a
	 * ResultSet.
	 *
	 * @param image Image content as InputStream.
	 * @param carId Id of car.
	 * @return ResultSet containing entry of image.
	 */
	public ResultSet putImage(InputStream image, int carId) {
		try {
			String query = "INSERT INTO images (carID, image) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, carId);
			ps.setBlob(2, image);
			ps.executeUpdate();

			query = "SELECT * FROM images WHERE id=LAST_INSERT_ID();";
			return db.retrieve(con, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method creates and runs a SQL query
	 * that gathers images belonging to the given
	 * car. This method then returns the images
	 * in a ResultSet.
	 *
	 * @param carId Id of car.
	 * @return ResultSet of all images belonging to given car.
	 */
	public ResultSet getImages(int carId){
		String query = "SELECT * FROM images WHERE carID=" +carId;

		return db.retrieve(con, query);
	}

	/**
	 * This method creates and runs a SQL query that
	 * gets the car id for a specific image and returns
	 * it in a ResultSet.
	 *
	 * @param id Id of image.
	 * @return ResultSet containing id of car corresponding to image.
	 */
	public ResultSet carIdOfImage(int id) {
		String query = "SELECT carID FROM images WHERE id=" +id;
		return db.retrieve(con, query);
	}

	/**
	 * This method deletes an entry from the images
	 * table with the given image id.
	 *
	 * @param id Id of image to delete.
	 */
	public void deleteImage(int id) {
		String query = "DELETE FROM images WHERE id=\'"+id+"\'";
		db.delete(con, query);
	}
}
