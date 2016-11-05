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
	
	public ArrayList<Car> getCars(){
		ResultSet rs = null; 
		rs = cp.getCars(); 
		
		ArrayList<Car>resultCars = new ArrayList<Car>(); 
		
		try {
			while(rs.next()){
				String make; 
				String model; 
				int carYear; 
				String color; 
				int price; 
				int id; 
				String description; 
				String carType;
				
				make = rs.getString("make"); 
				model = rs.getString("model"); 
				carYear = rs.getInt("carYear"); 
				color = rs.getString("color"); 
				price = rs.getInt("price"); 
				id = rs.getInt("ownerID"); 
				description = rs.getString("description"); 
				carType = rs.getString("carType");
				//retrieves info from the result set. 
				
				resultCars.add(new Car(make, model, carYear, color, id, price, description, carType)); 
				//dynamically creates car objects. 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultCars; 
	}
	
}
