package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<String> getTypes() {
		List<String> types = new ArrayList<String>();
		ResultSet rs = cp.getTypes();
		String type;
		try {
			while(rs.next()){
				type = rs.getString("carType");
				if(!types.contains(type))
					types.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}

	public List<String> getModels() {
		List<String> models = new ArrayList<String>();
		ResultSet rs = cp.getModels();
		String model;
		try {
			while(rs.next()){
				model = rs.getString("model");
				if(!models.contains(model))
					models.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return models;
	}

	public List<String> getMakes() {
		List<String> makes = new ArrayList<String>();
		ResultSet rs = cp.getMakes();
		String make;
		try {
			while(rs.next()){
				make = rs.getString("make");
				if(!makes.contains(make))
					makes.add(make);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return makes;
	}
	
}
