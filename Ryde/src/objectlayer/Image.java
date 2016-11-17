package objectlayer;

public class Image {
	private int id;
	private String image;
	private int carId;
	
	
	
	/**
	 * @param id
	 * @param image
	 * @param carId
	 */
	public Image(int id, String image, int carId) {
		this.id = id;
		this.image = image;
		this.carId = carId;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	
}
