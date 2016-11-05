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
		//rs = cp.getCars(); 
		
		ArrayList<Car>resultCars = new ArrayList<Car>(); 
		
		while(rs.next()){
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
}
