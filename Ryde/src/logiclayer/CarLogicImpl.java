package logiclayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Part;

import com.ibm.wsdl.util.IOUtils;

import objectlayer.Car;
import persistlayer.CarPersistImpl;

//This class is the controller class for all the functionalities of cars.
//It directly interacts with the Servlet and car persistent layer classes.
public class CarLogicImpl {
	private CarPersistImpl cp;
	
	public CarLogicImpl(){
		cp = new CarPersistImpl();
	}
	
	public List<Car> getCars(){
		ResultSet rs = null; 
		rs = cp.getCars(); 
		
		List<Car>resultCars = new ArrayList<Car>(); 
		
		try {
			while(rs.next()){
				String make; 
				String model; 
				int carYear; 
				String color; 
				int price; 
				int id;
				int ownerId; 
				String description; 
				String carType;
				
				make = rs.getString("make"); 
				model = rs.getString("model"); 
				carYear = rs.getInt("carYear"); 
				color = rs.getString("color"); 
				price = rs.getInt("price"); 
				id = rs.getInt("id");
				ownerId = rs.getInt("ownerID"); 
				description = rs.getString("description"); 
				carType = rs.getString("carType");
				//retrieves info from the result set. 
				
				Car car = new Car(id, make, model, carYear, color, ownerId, price, description, carType);
				
				resultCars.add(car); 
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

	public Car getCar(int id) {
		ResultSet rs = cp.getCar(id);
		try {
			if(rs.next())
				return new Car(id, rs.getString("make"), rs.getString("model"), 
						rs.getInt("carYear"), rs.getString("color"), 
						rs.getInt("ownerId"), rs.getInt("price"), 
						rs.getString("description"), rs.getString("carType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Car> getCarsFromUser(int ownerId) {
		List<Car> cars = new ArrayList<Car>(); 
		ResultSet rs = cp.getCarsFromUser(ownerId); 
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String make = rs.getString("make");
				String model = rs.getString("model");
				int year = rs.getInt("carYear");
				String color = rs.getString("color");
				int price = rs.getInt("price");
				String description = rs.getString("description");
				String carType = rs.getString("carType");
				
				Car car = new Car(id, make, model, year, color, ownerId, price, description, carType);
				cars.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cars;
	}

	//returns id of the car after it is inserted into the db
	public int addCar(int ownerId, String make, String model, String year, String color, 
			String price, String description, String carType) {
		ResultSet rs = cp.addCar(ownerId, make, model, year, color, price, description, carType);
		try {
			if(rs.next());
				return rs.getInt("LAST_INSERT_ID()");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;//-1 if messed up
	}

	public void editCar(int id, String make, String model, String year, String color, String price,
			String description, String carType) {
		cp.editCar(id, make, model, year, color, price, description, carType);
		
	}

	public int getOwnerId(int id) {
		ResultSet rs = cp.getOwnerId(id);
		try {
			rs.next();
			return(rs.getInt("ownerID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public void deleteCar(int id) {
		cp.deleteCar(id);
		
	}

	public void putImage(InputStream image, int carId) {
		cp.putImage(image, carId);
	}
	
	public List<String> getImages(int carId){
		ResultSet rs = cp.getImages(carId);
		List<String> images = new ArrayList<String>();
		try {
			while(rs.next()){
				Blob blob = rs.getBlob("image");
				InputStream img = blob.getBinaryStream();
				byte[] bytes = blob.getBytes(1, (int) blob.length());
				byte[] imgBytesAsBase64 = Base64.getEncoder().encode(bytes);
				String imgDataAsBase64 = new String(imgBytesAsBase64);
				String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
				images.add(imgAsBase64);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return images;
	}
	
}
