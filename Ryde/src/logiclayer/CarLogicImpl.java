package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objectlayer.Car;
import persistlayer.CarPersistImpl;

//This class is the controller class for all the functionalities of cars.
//It directly interacts with the Servlet and car persistent layer classes.
public class CarLogicImpl {
	private CarPersistImpl cp;
	
	public CarLogicImpl(){
		cp = new CarPersistImpl();
	}
	
	public ArrayList<Car> getCars() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = null; 
		rs = cp.getCars(); 
		
		ArrayList<Car>resultCars = new ArrayList<Car>(); 
		
		while(rs.next()){
			String make; 
			String model; 
			int carYear; 
			String color; 
			int price; 
			int id; 
			String description; 
			String bodystyle; 
			
			make = rs.getString("make"); 
			model = rs.getString("model"); 
			carYear = rs.getInt("carYear"); 
			color = rs.getString("color"); 
			price = rs.getInt("price"); 
			id = rs.getInt("userId"); 
			description = rs.getString("userDescription"); 
			bodystyle = rs.getString("bodystyle"); 
			//retrieves info from the result set. 
			
			resultCars.add(new Car(model, carYear, color, price, id, description, bodystyle)); 
			//dynamically creates car objects. 
		}
		
		return resultCars; 
	}
	
	public Car getSingleCar(String fn, String ln) throws SQLException
	{
		ResultSet rs = null; 
		
		Car returnCar = null; 
		
		while(rs.next())
		{
						
			String model = rs.getString("model"); 
			int year = rs.getInt("year"); 
			String color = rs.getString("color"); 
			int price = rs.getInt("price"); 
			int id = rs.getInt("id"); 
			String description = rs.getString("description"); 
			String bodystyle = rs.getString("bodystyle"); 
			
			
			
			returnCar = new Car(model, year, color, price, id, description, bodystyle); 
		}
		
		return returnCar; 
		//used to get single car. 
		
	}
	
	
	
	public boolean canRent(String startDate, String endDate)
	{
		ResultSet rs = null; 
		
		rs = cp.checkRental(startDate, endDate); 
		
		if (rs == null)
			return true; 
		else 
			return false; 
		//verifys if user can rent. If they can will return empty (null and thus true, if not false). 
		
	}
	
}
