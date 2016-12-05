package objectlayer;



/**
 * 
 * This class is used to create and return rental objects. 
 * Interacts with all layers as allowed by MVC. 
 * @author SahiComputer
 *
 */
public class Rental 
{
	private String startDate; 
	private String endDate; 
	private int carID; 
	private int renterID;
	private int id;
	
	/**
	 * Constructor that creates rental object. 
	 * 
	 * @param startDate Start date of the rental.
	 * @param endDate End date of the rental.
	 * @param carID Id of the car being rented.
	 * @param renterID Id of the user renting.
	 * @param id Unique id of the rental object.
	 */
	public Rental(String startDate, String endDate, int carID, int renterID, int id) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.carID = carID;
		this.renterID = renterID;
		this.id = id;
	}

	/**
	 * This method is the getter for startDate.
	 * @return The start date of the rental.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * This method is the setter for startDate.
	 * @param startDate The start date of the rental
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * This method is the getter for endDate.
	 * @return The end date of the renal.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * This method is the setter for endDate.
	 * @param endDate The end date of the rental.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * This method is the getter for carID.
	 * @return The id of the car being rented.
	 */
	public int getCarID() {
		return carID;
	}

	/**
	 * This method is the setter for carID.
	 * @param carID The id of the car being rented.
	 */
	public void setCarID(int carID) {
		this.carID = carID;
	}

	/**
	 * This method is the getter for renterID.
	 * @return The id of the user renting.
	 */
	public int getRenterID() {
		return renterID;
	}

	/**
	 * This method is the setter for renteID.
	 * @param renterID The id of the user renting.
	 */
	public void setRenterID(int renterID) {
		this.renterID = renterID;
	}

	/**
	 * This method is the getter for id.
	 * @return The id of the rental object.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method is the setter for id.
	 * @param id The id of the rental object.
	 */
	public void setId(int id) {
		this.id = id;
	}

}
