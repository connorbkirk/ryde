package Ryde.logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Ryde.objectlayer.Car;
import Ryde.objectlayer.User;
import Ryde.persistlayer.RydePersistImpl;

public class CarLogicImpl 

{
	private RydePersistImpl rpi; 
	
	
	public CarLogicImpl() throws ClassNotFoundException
	{
		rpi = new RydePersistImpl(); 
	}
	
	
	public ArrayList<Car> getCars() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = null; 
		rs = rpi.returnCars(); 
		
		ArrayList<Car>resultCars = new ArrayList<Car>(); 
		
		
		while(rs.next())
		{
			String make; 
			String model; 
			int carYear; 
			String color; 
			int price; 
			int id; 
			String description; 
			
			make = rs.getString("make"); 
			model = rs.getString("model"); 
			carYear = rs.getInt("carYear"); 
			color = rs.getString("color"); 
			price = rs.getInt("price"); 
			id = rs.getInt("userId"); 
			description = rs.getString("userDescription"); 
			//retrieves info from the result set. 
			
			
			resultCars.add(new Car(model, carYear, color, price, id, description)); 
			//dynamically creates car objects. 
			
			
		}
		
		return resultCars; 
		
	}
	
	public Car getSingleUser(String fn, String ln) throws SQLException
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
			
			
			
			returnCar = new Car(model, year, color, price, id, description); 
		}
		
		return returnCar; 
		
	}
	
	
	
	
}
