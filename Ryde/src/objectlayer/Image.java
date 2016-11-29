package objectlayer;

/**
 * This class represents the model of an image object.
 * 
 * @author Connor Kirk
 *
 */
public class Image {
	private int id;
	private String image;
	private int carId;
	
	/**
	 * This is the constructor for the object.
	 * It requires the id, image content as
	 * a string, and the id of the car it 
	 * belongs to.
	 * 
	 * @param id The id of the image.
	 * @param image The contents of the image.
	 * @param carId The id of the car the image belongs to.
	 */
	public Image(int id, String image, int carId) {
		this.id = id;
		this.image = image;
		this.carId = carId;
	}
	/**
	 * @return The id of the image.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param The id of the image.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return The contents of the image as a string.
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param The contents of the image as a string.
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return The id of the car the image belongs to.
	 */
	public int getCarId() {
		return carId;
	}
	/**
	 * @param The id of the car the image belongs to.
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	
}
