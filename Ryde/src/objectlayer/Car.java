package objectlayer;


//this class is the model of the car
public class Car {
	private String make;
	private String model;
	private int year;
	private String color;
	private int ownerId;
	private int price;
	private String description;
	private String bodystyle;

	public Car (String userModel, int userYear, String userColor, int userPrice, int inputID, String userDescription, String bodystyle){
		make = userModel; 
		year = userYear; 
		color = userColor; 
		price = userPrice; 
		ownerId = inputID; 
		description = userDescription; 
		this.bodystyle = bodystyle;
	}
	
	/**
	 * @return the bodystyle
	 */
	public String getBodystyle() {
		return bodystyle;
	}

	/**
	 * @param bodystyle the bodystyle to set
	 */
	public void setBodystyle(String bodystyle) {
		this.bodystyle = bodystyle;
	}
	
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
