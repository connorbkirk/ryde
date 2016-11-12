package boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logiclayer.CarLogicImpl;
import logiclayer.UserLogicImpl;
import objectlayer.Car;
import objectlayer.User;

/**
 * Servlet implementation class Servlet
 */
//this class handles all the requests and responses. 
//It directly interacts with the controller class.
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	//global variables
		private static final long serialVersionUID = 1L;
		private Configuration cfg = null;					//config
		private String templateDir = null;					//template directory
		private Template template = null;					//template object
		private DefaultObjectWrapperBuilder df = null;		//for use with freemarker
		private SimpleHash root = null;						//root map for use with freemarker
		PrintWriter out = null;								//writer for output
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }

    public void init() {
    	//intialized global vars
    	df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);//set object wrapper builder
		
		templateDir = "/WEB-INF/templates";//set directory
    	
    	// Create your Configuration instance, and specify if up to what FreeMarker
    	// version (here 2.3.25) do you want to apply the fixes that are not 100%
    	// backward-compatible. See the Configuration JavaDoc for details.
    	cfg = new Configuration(Configuration.VERSION_2_3_25);
    	
    	// Specify the source where the template files come from.
		cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
		
		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//This function processes the request of the user and redirects them to the proper method
		response.setContentType("text/html");
		out = response.getWriter();//initialize writer
		root = new SimpleHash(df.build());//initialize root map
		
		//route request
		//--> use parameter --> req
		switch(request.getParameter("req")){
			case "register":
				register(request, response);
				break;
			case "login":
				login(request, response);
				break;
			case "logout":
				logout(request, response);
				break;
			case "search":
				search(request);
				break;
			case "car":
				viewCar(request);
				break;
			case "user":
				viewUser(request);
				break;
			case "add":
				add(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				break;
			
		}
	}
	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		//make sure that the user is editing his/her own entry
		Integer userId = (Integer) session.getAttribute("user");
		String id = request.getParameter("id");
		int ownerId = carCtrl.getOwnerId(id);
		
		//route the user to login if user is not logged in
		if(userId == null){
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//route the user if they are trying to edit a car they do not own
		}else if(userId != ownerId){
			//route user back to previous page or send to access forbidden page
			//for now, route to search
			try {
				response.sendRedirect("Servlet?req=search");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			carCtrl.deleteCar(id);
			root.put("userId", userId);
			processTemplate("delete.ftl");
		}
		
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) {
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		//make sure that the user is editing his/her own entry
		Integer userId = (Integer) session.getAttribute("user");
		String id = request.getParameter("id");
		int ownerId = carCtrl.getOwnerId(id);
		
		//route the user to login if user is not logged in
		if(userId == null){
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//route the user if they are trying to edit a car they do not own
		}else if(userId != ownerId){
			//route user back to previous page or send to access forbidden page
			//for now, route to search
			try {
				response.sendRedirect("Servlet?req=search");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			
			String make = request.getParameter("make");
			String model = request.getParameter("model");
			String year = request.getParameter("year");
			String color = request.getParameter("color");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String carType = request.getParameter("type");
			
			if(make!=null && model != null && year != null && color != null && price != null
					&& description != null && carType != null){
				Car car = new Car(Integer.parseInt(id), make, model, Integer.parseInt(year), 
					color, ownerId, Integer.parseInt(price), description, carType);
			
				carCtrl.editCar(id, make, model, year, color, price, description, carType);
				root.put("done", true);
				root.put("car", car);
			}else{
				root.put("car", carCtrl.getCar(id));
			}
			
			root.put("userId", userId);
			processTemplate("edit.ftl");
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String color = request.getParameter("color");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String carType = request.getParameter("type");
		
		if(session.getAttribute("user") != null){
			//if all parameters are not null, insert car into db.
			//deal with making sure params are not null with js
			if(make!=null && model != null && year != null && color != null && price != null
					&& description != null && carType != null){
				int ownerId = (Integer) session.getAttribute("user");
				
				int carId = carCtrl.addCar(ownerId, make, model, year, color, 
						price, description, carType);
				root.put("id", carId);
			}
			root.put("userId", session.getAttribute("user"));
			processTemplate("add.ftl");
		}
		else{
			//if user is not logged in, route to login page
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void viewUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserLogicImpl userCtrl = new UserLogicImpl();
		CarLogicImpl carCtrl = new CarLogicImpl();
		
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userCtrl.getSingleUser(id);
		List<Car> cars = carCtrl.getCarsFromUser(id);
		
		root.put("user", user);
		root.put("cars", cars);
		
		//add additional object to map to give user extra
		//functionalities if they are viewing their own page
		Integer userIdFromSession = (Integer) session.getAttribute("user");
		if(userIdFromSession != null){
			root.put("userId", userIdFromSession);
			if(userIdFromSession == id)
				root.put("same", true);
		}
		processTemplate("user.ftl");
	}
	
	void viewCar(HttpServletRequest request){
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		Car car = carCtrl.getCar(id);
		root.put("car", car);
		
		//add stuff here to let user edit car if they own it
		Integer userId = (Integer) session.getAttribute("user");
		if(userId!=null){
			root.put("userId", session.getAttribute("user"));
			int ownerId = carCtrl.getOwnerId(id);
			if(userId == ownerId){
				root.put("same", true);
			}
			
		}
		processTemplate("car.ftl");
	}
	
	void search(HttpServletRequest request) {
		CarLogicImpl carCtrl = new CarLogicImpl();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null);
			root.put("userId", session.getAttribute("user"));
		
		//gathers params
		String from = request.getParameter("fromDate");
		String to = request.getParameter("toDate");
		String type = request.getParameter("type");
		String make = request.getParameter("make");
		String sort = request.getParameter("sortBy");
		
		//not actually taking dates into consideration yet
		//work on that
		List<Car> cars = carCtrl.getCars();
		
		List<String> types = carCtrl.getTypes();
		List<String> makes = carCtrl.getMakes();
		List<String> models = carCtrl.getModels();
		root.put("types", types);
		root.put("makes", makes);
		root.put("models", models);
		root.put("cars",cars);
		
		processTemplate("search.ftl");
		
	}
	
	void register(HttpServletRequest request, HttpServletResponse response){
		
		UserLogicImpl userCtrl = new UserLogicImpl();
		
		//gather parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		userCtrl.register(username, password, firstName, lastName);
		//must check to make sure values do not exist in db
		
		//set session id stuff
		HttpSession session = request.getSession();
		session.setAttribute("user", userCtrl.getIdFromUsername(username));
		
		//redirect user
		try {
			response.sendRedirect("Servlet?req=search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void login(HttpServletRequest request, HttpServletResponse response){
		
		UserLogicImpl userCtrl = new UserLogicImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if(userCtrl.validate(username, password)){
				HttpSession session = request.getSession();
				session.setAttribute("user", userCtrl.getIdFromUsername(username));
				out.print("valid login");
				response.sendRedirect("Servlet?req=search");
			}
			else
				out.print("invalid login");//do something with js?
		} catch (NoSuchAlgorithmException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processTemplate(String templateName){
		//this method processes the template with the given template name
		try {
			template = cfg.getTemplate(templateName);
			template.process(root, out);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
