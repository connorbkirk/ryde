package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objectlayer.Rental;
import persistlayer.RentalPersistImpl;


public class RentalLogicImpl
{

	private RentalPersistImpl rp; 
	/**
	 * Constructor that creates a persist layer object.
	 */
	public RentalLogicImpl()
	{
		rp = new RentalPersistImpl(); 
	}

	/**
	 * This method takes two parameters - a start date 
	 * and an end date formatted as MM/dd/yy. These strings
	 * are converted into Date objects and then verified.
	 * If the two dates are valid, return true; else return false.
	 * 
	 * @param startDate Start date formatted as MM/dd/yyyy.
	 * @param endDate End date formatted as MM/dd/yy.
	 * @return True if valid; false otherwise.
	 */
	public boolean verifyDates(String startDate, String endDate){
		boolean verified = false;

		try {
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);

			verified = true;

			if(start.after(end) || end.before(start))
				verified = false;
			if(start.equals(end))
				verified = false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return verified;
	}

	/**
	 * This method takes one parameter - a car ID. This method 
	 * calls the persist layer to gather all rental dates 
	 * for the given car ID. This method processes the results
	 * into a List of Rental objects and returns it.
	 * 
	 * @param carId Id of car to get rental dates for.
	 * @return returns A List of Rental objects for the given car ID.
	 */
	public ArrayList<Rental> viewUnavailable(int carId)
	{
		ArrayList<Rental> rentals = new ArrayList<Rental>(); 

		ResultSet rs = rp.viewUnavailable(carId); 
		//returns all taken dates. 

		if (rs != null)
		{
			//if there are dates taken iterate and create js variable to be parsed. 
			try {
				while(rs.next())
				{
					String day1 = rs.getString("startDate"); 
					String day2 = rs.getString("endDate"); 
					int id = rs.getInt("id");
					//int carId = rs.getInt("carID");
					int renterId = rs.getInt("renterID");
					rentals.add(new Rental(day1, day2, carId, renterId, id)); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rentals; 
	}

	/**
	 * This method takes four parameters - a start date, an end date,
	 * a car ID, and a renter ID. This method converts the dates to
	 * the proper SQL format and calls the persist layer to create
	 * a new entry in the database.
	 * 
	 * @param startDate Start date of rental formatted as MM/dd/yyyy.
	 * @param endDate End date of rental formatted as MM/dd/yyyy.
	 * @param carId Id of the car being rented.
	 * @param renterId The id of the user booking the car.
	 */
	public void addRentalDate(String startDate, String endDate, int carId, int renterId)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//convert string to date object
			Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
			Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
			rp.addRentalDate(df.format(start), df.format(end), carId, renterId); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method takes a user ID as a parameter.
	 * It calls the persist layer to gather all rental
	 * entries for the corresponding user ID. Then it
	 * processes the results and returns them as a
	 * List of rental objects.
	 * 
	 * @param userId Id of user to search.
	 * @return List of rental objects containing the given user ID.
	 */
	public ArrayList<Rental> getUserRentals(int userId){
		ArrayList<Rental> rentals = new ArrayList<Rental>(); 

		//query database
		ResultSet rs = rp.viewUserRented(userId);

		try {	
			//process result set
			while(rs.next()){
				String day1 = rs.getString("startDate"); 
				String day2 = rs.getString("endDate"); 
				int id = rs.getInt("id");
				int carId = rs.getInt("carID");
				int renterId = rs.getInt("renterID");

				Rental rental = new Rental(day1, day2, carId, renterId, id);

				rentals.add(rental);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rentals;
	}

	/**
	 * This method takes the id of a rental entry
	 * in the database and returns the user id 
	 * of the renter.
	 * 
	 * @param id Rental id to search.
	 * @return User id of the renter of the given rental id.
	 */
	public int getRenterId(int id) {
		ResultSet rs = rp.getRental(id);
		try {
			if(rs.next())
				return rs.getInt("renterID");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;//error
	}

	/**
	 * This method takes the id of a rental entry
	 * in the database and calls the persist
	 * layer to remove it.
	 * 
	 * @param id Id of the rental entry to delete.
	 */
	public void cancelRental(int id) {
		rp.deleteRental(id);
	}

}
