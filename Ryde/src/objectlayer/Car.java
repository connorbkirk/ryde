package objectlayer;

import java.util.List;

/**
 * This class represents the model of a car object.
 * 
 * @author Connor Kirk
 *
 */
public class Car {
	private int id;
	private String make;
	private String model;
	private int year;
	private String color;
	private int ownerId;
	private int price;
	private String description;
	private String carType;
	private List<Image> images;
	
	/**
	 * This is the constructor for the Car object.
	 * It requires all of the car information to create.
	 * 
	 * @param id The id of the car.
	 * @param make The make of the car.
	 * @param model The model of the car.
	 * @param year The year of the car.
	 * @param color The color of the car.
	 * @param ownerId The id of the owner of the car.
	 * @param price The price per day of the car.
	 * @param description The description of the car.
	 * @param carType The body type of the car.
	 * @param images A list of Image objects of the car.
	 */
	public Car(int id, String make, String model, int year, String color, int ownerId, int price, String description,
			String carType, List<Image> images) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.ownerId = ownerId;
		this.price = price;
		this.description = description;
		this.carType = carType;
		this.images = images;
	}
	
	/**
	 * This method is the getter for images.
	 * @return A list of Images of the car.
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * This method is the setter for images.
	 * @param images A list of Images of the car.
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/**
	 * This method is the getter for id.
	 * @return The car's id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * This method is the setter for id.
	 * @param id The car's id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * This method is the getter for make.
	 * @return The car's make.
	 */
	public String getMake() {
		return make;
	}
	/**
	 * This methd is the setter for make.
	 * @param make The car's make.
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * This method is the getter for model.
	 * @return The car's model.
	 */
	public String getModel() {
		return model;
	}
	/**
	 * This method is the setter for model.
	 * @param model The car's model.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * This method is the getter for year.
	 * @return The car's year.
	 */
	public int getYear() {
		return year;
	}
	/**
	 * This method is the setter for year.
	 * @param year The car's year.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * This method is the getter for color.
	 * @return The car's color.
	 */
	public String getColor() {
		return color;
	}
	/**
	 * This method is the setter for color.
	 * @param color The car's color.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * This method is the getter for ownerId.
	 * @return The id of the owner of the car.
	 */
	public int getOwnerId() {
		return ownerId;
	}
	/**
	 * This method is the setter for ownerId.
	 * @param ownerId The id of the owner of the car.
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * This method is the getter for price.
	 * @return The price per day of the car.
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * This method is the setter for price.
	 * @param price The price per day of the car.
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * This method is the getter for description.
	 * @return The description of the car.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This method is the setter for description.
	 * @param description The description of the car.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * This method is the getter for carType.
	 * @return The body type of the car.
	 */
	public String getCarType() {
		return carType;
	}
	/**
	 * This method is the setter for carType.
	 * @param carType The body type of the car.
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
}
