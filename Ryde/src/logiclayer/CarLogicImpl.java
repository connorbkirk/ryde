package logiclayer;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import objectlayer.Car;
import objectlayer.Image;
import persistlayer.CarPersistImpl;

/**
 * This class is the controller class for all the functionalities of cars.
 * It directly interacts with the Servlet and car persistent layer classes.
 * @author Connor Kirk
 */
public class CarLogicImpl {
	private CarPersistImpl cp;
	
	/**
	 * Default constructor. Creates a new object of CarPersistImpl.
	 */
	public CarLogicImpl(){
		cp = new CarPersistImpl();
	}
	
	/**
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet of all calls in the database. It then 
	 * processes the ResultSet into a list of Car objects.
	 * 
	 * @return A list of car objects.
	 */
	public List<Car> getCars(){
		ResultSet rs = null; 
		rs = cp.getCars(); 
		
		List<Car>resultCars = new ArrayList<Car>(); 
		
		try {
			while(rs.next()){
				String make = rs.getString("make"); 
				String model = rs.getString("model");
				int carYear = rs.getInt("carYear"); 
				String color = rs.getString("color"); 
				int price = rs.getInt("price"); 
				int id = rs.getInt("id");
				int ownerId = rs.getInt("ownerID"); 
				String description = rs.getString("description"); 
				String carType = rs.getString("carType");
				//retrieves info from the result set. 
				
				Car car = new Car(id, make, model, carYear, color, ownerId, price, description, carType, getImages(id));
				
				resultCars.add(car); 
				//dynamically creates car objects. 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultCars; 
	}
	
	/**
	 * 
	 * @param make
	 * @param carType
	 * @return
	 */
	public List<Car> getCarsWithParams(String make, String carType){
		ResultSet rs = null;
		rs = cp.getCarsWithParams(make, carType); 

		List<Car>resultCars = new ArrayList<Car>();

		try {
			while(rs.next()){
				//String make = rs.getString("make");
				String model = rs.getString("model");
				int carYear = rs.getInt("carYear");
				String color = rs.getString("color");
				int price = rs.getInt("price");
				int id = rs.getInt("id");
				int ownerId = rs.getInt("ownerID");
				String description = rs.getString("description");
				//String carType = rs.getString("carType");
				//retrieves info from the result set.

				Car car = new Car(id, make, model, carYear, color, ownerId, price, description, carType, getImages(id));

				resultCars.add(car);
				//dynamically creates car objects.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultCars;
	}

	/**
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet of all types in the database. It then 
	 * processes the ResultSet into a list of strings containing
	 * all of the types.
	 * 
	 * @return A list of strings containing types of cars.
	 */
	public List<String> getTypes() {
		List<String> types = new ArrayList<String>();
		ResultSet rs = cp.getTypes();
		String type;
		try {
			while(rs.next()){
				type = rs.getString("carType");
				if(!types.contains(type))
					types.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}

	/**
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet of all models in the database. It then 
	 * processes the ResultSet into a list of strings containing
	 * all of the models.
	 * 
	 * @return A list of strings containing models of cars.
	 */
	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		ResultSet rs = cp.getModels();
		String model;
		try {
			while(rs.next()){
				model = rs.getString("model");
				if(!models.contains(model))
					models.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return models;
	}

	/**
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet of all makes in the database. It then 
	 * processes the ResultSet into a list of strings containing
	 * all of the makes.
	 * 
	 * @return A list of strings containing makes of cars.
	 */
	public List<String> getMakes() {
		List<String> makes = new ArrayList<String>();
		ResultSet rs = cp.getMakes();
		String make;
		try {
			while(rs.next()){
				make = rs.getString("make");
				if(!makes.contains(make))
					makes.add(make);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return makes;
	}

	/**
	 * 
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet containing an entry the car with the id
	 * given as a parameter. It then processes the ResultSet 
	 * into a single Car object.
	 * 
	 * @param id The id of the car to get.
	 * @return The corresponding Car object.
	 */
	public Car getCar(int id) {
		ResultSet rs = cp.getCar(id);
		try {
			if(rs.next())
				return new Car(id, rs.getString("make"), rs.getString("model"), 
						rs.getInt("carYear"), rs.getString("color"), 
						rs.getInt("ownerId"), rs.getInt("price"), 
						rs.getString("description"), rs.getString("carType"), getImages(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * This method calls the CarPersistImpl class to gather
	 * a ResultSet of all calls belonging to a given user
	 * in the database. It then processes the ResultSet 
	 * into a list of Car objects.
	 * 
	 * @param ownerId The id of the owner whose cars to get.
	 * @return A list of car objects.
	 */
	public List<Car> getCarsFromUser(int ownerId) {
		List<Car> cars = new ArrayList<Car>(); 
		ResultSet rs = cp.getCarsFromUser(ownerId); 
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = rs.getInt("carYear");
				String color = rs.getString("color");
				int price = rs.getInt("price");
				String description = rs.getString("description");
				String carType = rs.getString("carType");
				
				Car car = new Car(id, make, model, year, color, ownerId, price, description, carType, getImages(id));
				cars.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cars;
	}

	/**
	 * 
	 * This method takes information about a car and 
	 * calls the CarPersistImpl to insert it into the
	 * database. This method will then return the id
	 * of the car after it was inserted.
	 * 
	 * @param ownerId Id of owner.
	 * @param make Make of car.
	 * @param model Model of car.
	 * @param year Year of car.
	 * @param color Color of ccar.
	 * @param price Price per day of car.
	 * @param description Description of car.
	 * @param carType Body type of car.
	 * @return The id of the car after it is inserted in the database.
	 */
	public int addCar(int ownerId, String make, String model, String year, String color, 
			String price, String description, String carType) {
		ResultSet rs = cp.addCar(ownerId, make, model, year, color, price, description, carType);
		try {
			if(rs.next());
				return rs.getInt("LAST_INSERT_ID()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;//-1 if messed up
	}

	/**
	 * This method takes information about a car and 
	 * calls the CarPersistImpl to edit the entry
	 * in the database.
	 * 
	 * @param id Id of the car.
	 * @param make Make of the car.
	 * @param model Model of the car.
	 * @param year Year of the car.
	 * @param color Color of the car.
	 * @param price Price per day of the car.
	 * @param description Description of the car.
	 * @param carType Body type of the car.
	 */
	public void editCar(int id, String make, String model, String year, String color, String price,
			String description, String carType) {
		cp.editCar(id, make, model, year, color, price, description, carType);
		
	}

	/**
	 * This method calls the CarPersistImpl class to
	 * gather a ResultSet containing the ownerId of
	 * the car with the given id. This method then
	 * processes the ResultSet and returns a string
	 * containing the ownerId of the car.
	 * 
	 * @param id Id of the car.
	 * @return Id of the owner.
	 */
	public int getOwnerId(int id) {
		ResultSet rs = cp.getOwnerId(id);
		try {
			rs.next();
			return(rs.getInt("ownerID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * This method calls the CarPersistImpl to delete
	 * the entry in the database with the given carId.
	 * 
	 * @param id Id of the car to delete.
	 */
	public void deleteCar(int id) {
		cp.deleteCar(id);
	}

	/**
	 * This method calls the CarPersistImpl class to
	 * insert the given image and carId into the image
	 * table, and also return a ResultSet containing the
	 * entry. This method will then process the ResultSet
	 * to create an Image object containing the image as a
	 * String.
	 * 
	 * @param image InputStream of image.
	 * @param carId Id of car.
	 * @return Image object of given image and carId.
	 */
	public Image putImage(InputStream image, int carId) {
		ResultSet rs = cp.putImage(image, carId);
		try {
			if(rs.next()){
				int id = rs.getInt("id");
				Blob blob = rs.getBlob("image");
				InputStream img = blob.getBinaryStream();
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				byte[] imgBytesAsBase64 = Base64.getEncoder().encode(bytes);
				String imgDataAsBase64 = new String(imgBytesAsBase64);
				String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
				
				return new Image(id, imgAsBase64, carId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * This method calls the CarPersistImpl class to
	 * gather all entries in the images table corresponding
	 * to the given carId, process the ResultSet into a 
	 * list of Image objects, and return the list.
	 * 
	 * @param carId Id of the car.
	 * @return List of Image objects that belong to carId.
	 */
	public List<Image> getImages(int carId){
		ResultSet rs = cp.getImages(carId);
		List<Image> images = new ArrayList<Image>();
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				
				Blob blob = rs.getBlob("image");
				InputStream img = blob.getBinaryStream();
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				byte[] imgBytesAsBase64 = Base64.getEncoder().encode(bytes);
				String imgDataAsBase64 = new String(imgBytesAsBase64);
				String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
				images.add(new Image(id, imgAsBase64, carId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return images;
	}

	/**
	 * This method calls the CarPersistImpl to create a
	 * ResultSet containing car information of a given 
	 * image. This method then returns the owner of the car
	 * as an int.
	 * 
	 * @param id Id of the image.
	 * @return Id of the owner of the image and car.
	 */
	public int getOwnerOfImage(int id) {
		ResultSet rs = cp.carIdOfImage(id);
		try {
			if(rs.next()){
				int carId = rs.getInt("carID");
				return getOwnerId(carId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 
	 * This method calls the CarPersistImpl to remove an
	 * entry from the images table with the corresponding
	 * id.
	 * 
	 * @param id Id of the image.
	 */
	public void deleteImage(int id) {
		cp.deleteImage(id);
		
	}
	
}
