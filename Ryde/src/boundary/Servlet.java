package boundary;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import logiclayer.CarLogicImpl;
import logiclayer.UserLogicImpl;
import objectlayer.Car;
import objectlayer.Image;
import objectlayer.User;

/**
 * Servlet class.
 * This class handles all requests and responses.
 * It directly interacts with the controller class.
 * 
 * @author Connor Kirk
 * @version 1.00, 5 Dec 2016
 */
@WebServlet("/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {
	//global variables
	private static final long serialVersionUID = 1L;
	private Configuration cfg = null;					//config
	private String templateDir = null;					//template directory
	private Template template = null;					//template object
	private DefaultObjectWrapperBuilder df = null;		//for use with freemarker
	private SimpleHash root = null;						//root map for use with freemarker
	private PrintWriter out = null;						//writer for output
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }

    /**
     * Initializes global variables and configures the servlet.
     * @see javax.servlet.GenericServlet#init()
     */
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
		processRequest(request, response);
	}
	
	/**
	 * This methods processes the request of the user and redirects to the proper method.
	 * 
	 * @param request Takes user's request. Gathers parameters
	 * @param response Used for redirecting the user.
	 * @throws IOException If there is an invalid request.
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		out = response.getWriter();//initialize writer
		root = new SimpleHash(df.build());//initialize root map
		
		//route request
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
			case "deleteImage":
				deleteImage(request, response);
				break;
			case "uploadImage":
				uploadImage(request, response);
				break;
			case "upload":
				upload(request, response);
				break;
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request");
				break;
		}
	}
	
	/**
	 * This method handles the upload request and directs the user's view to the
	 * upload.ftl template.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		int carId = Integer.parseInt(request.getParameter("id"));
		Integer userId = (Integer) session.getAttribute("user");
		root.put("carId", carId);
		if(userId!=null)
			root.put("userId", userId);
		else
			sendToLastPage(response, session);
		
		processTemplate("upload.ftl");
	}

	/**
	 * This method receives an image through the HttpServletRequest object,
	 * calls for the logic layer to store it in the database, and prints a 
	 * JSON object containing the image through the output stream to be 
	 * parsed with jquery.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for writing output to be used with JQuery.
	 */
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		int carId = Integer.parseInt(request.getParameter("id"));
		CarLogicImpl carCtrl = new CarLogicImpl();
		try {
			//upload image
			Part filePart = request.getPart("image");
			InputStream fileContent = filePart.getInputStream();
			Image image = carCtrl.putImage(fileContent, carId);
			
			//get json of image
			Gson gson = new Gson();
			String json = gson.toJson(image);
	        
			//create inputstream of json
			InputStream is = new ByteArrayInputStream(json.getBytes("UTF-8"));
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			
			//reset response so we can use outputstream
			response.reset();
			
			//write json to output
			do{
				bytesRead = is.read(buffer, 0, buffer.length);
				response.getOutputStream().write(buffer, 0, bytesRead);
			}while(bytesRead == buffer.length);
			
			
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * This method receives an image id through the HttpServletRequest object
	 * and calls for the car logic layer to remove it from the database. 
	 * If the user is not logged in, they will be redirected
	 * to the login page. If they are not owners of the car, they will be
	 * redirected to the last page they visited.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CarLogicImpl carCtrl = new CarLogicImpl();
		
		//make sure that the user is deleting his/her own image
		Integer userId = (Integer) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		int ownerId = carCtrl.getOwnerOfImage(id);
		
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
			sendToLastPage(response, session);
		}
		else{
			carCtrl.deleteImage(id);
		}
		
	}

	/**
	 * This method receives a car id through the HttpServletRequest object
	 * and calls for the car logic layer to remove the item from the database
	 * if the user has permission. If the user is not logged in, they will be 
	 * redirected to the login page. If they are not owners of the car, they 
	 * will be redirected to the last page they visited.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		//create logic and session objects
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		//make sure that the user is editing his/her own entry
		Integer userId = (Integer) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
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
			sendToLastPage(response, session);
		}
		else{
			carCtrl.deleteCar(id);
			root.put("userId", userId);
			processTemplate("delete.ftl");
		}
		
	}

	/**
	 * 
	 * This method receives a car id through the HttpServletRequest object.
	 * It allows the user to modify the car's entry in the database if they
	 * have permission. If the user is not logged in, they will be redirected
	 * to the login page. If they are not owners of the car, they will be
	 * redirected to the last page they visited.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		//create logic and session objects
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		//make sure that the user is editing his/her own entry
		Integer userId = (Integer) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
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
			sendToLastPage(response, session);
		}
		else{
			//gather params
			String make = request.getParameter("make");
			String model = request.getParameter("model");
			String year = request.getParameter("year");
			String color = request.getParameter("color");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String carType = request.getParameter("type");
			
			//if all the fields contain data, update the table the car table
			if(make!=null && model != null && year != null && color != null && price != null
					&& description != null && carType != null){
			
				carCtrl.editCar(id, make, model, year, color, price, description, carType);
				Car car = carCtrl.getCar(id);
				
				root.put("done", true);
				root.put("car", car);
			}else{//if any field is empty, do not allow user to edit car, instead return unaltered car
				root.put("car", carCtrl.getCar(id));
			}
			
			root.put("userId", userId);
			processTemplate("edit.ftl");
		}
	}

	/**
	 * This method receives information about a car through the
	 * HttpServletRequest object. It allows the user to make a new 
	 * entry in the database if they are logged in. If they are not
	 * logged in, they will be redirected to the login page.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		//gather params
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String color = request.getParameter("color");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String carType = request.getParameter("type");
		
		//if user is logged in, allow them to add 
		if(session.getAttribute("user") != null){
			//if all parameters are not null, insert car into db.
			if(make!=null && model != null && year != null && color != null && price != null
					&& description != null && carType != null){
				int ownerId = (Integer) session.getAttribute("user");
				int carId = carCtrl.addCar(ownerId, make, model, year, color, 
						price, description, carType);
				
				//route user to upload images
				try {
					response.sendRedirect("Servlet?req=upload&id="+carId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			root.put("userId", session.getAttribute("user"));
			processTemplate("add.ftl");
		}
		//if user is not logged in, route to login.html page
		else{
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * This method receives a user id through the HttpServletRequest
	 * object and calls for the user's information to be processed by
	 * FreeMarker with the user.ftl file. If a user is trying to view
	 * his/her own page, additional capabilities (edit/delete) will be
	 * shown on car listings.
	 * 
	 * @param request Stores the user's request and parameters.
	 */
	void viewUser(HttpServletRequest request){
		//create session and logic objects
		HttpSession session = request.getSession();
		UserLogicImpl userCtrl = new UserLogicImpl();
		CarLogicImpl carCtrl = new CarLogicImpl();
		
		//store url in session - for use when viewing forbidden pages
		session.setAttribute("url", getURL(request));
		
		//gather parameters
		int id = Integer.parseInt(request.getParameter("id"));
		
		//create model objects
		User user = userCtrl.getSingleUser(id);
		List<Car> cars = carCtrl.getCarsFromUser(id);
		
		//put objects in root map
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
	
	/**
	 * This method receives a car id through the HttpServletRequest
	 * object and calls for the car's information to be processed by
	 * FreeMarker with the car.ftl file. If a user is trying to view
	 * a car they own an edit button will be added to the page.
	 * 
	 * @param request Stores the user's request and parameters.
	 */
	void viewCar(HttpServletRequest request){
		//create session and logic objects
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		//store url in session - for use when viewing forbidden pages
		session.setAttribute("url", getURL(request));
		
		//gather params
		int id = Integer.parseInt(request.getParameter("id"));
		Car car = carCtrl.getCar(id);
		root.put("car", car);
		
		//let user edit car if they own it
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
	
	/**
	 * This method receives criteria through the HttpServletRequest
	 * object and uses it to narrow down the list of cars that will
	 * be processed and displayed using FreeMarker in the search.ftl
	 * file.
	 * 
	 * @param request Stores the user's request and parameters.
	 */
	void search(HttpServletRequest request) {
		//create logic and session objects
		CarLogicImpl carCtrl = new CarLogicImpl();
		HttpSession session = request.getSession();
		
		//store userId in session
		if(session.getAttribute("user")!=null);
			root.put("userId", session.getAttribute("user"));
		//store url in session - for use when viewing forbidden pages
		session.setAttribute("url", getURL(request));
		
		//gathers params
		String from = request.getParameter("fromDate");
		String to = request.getParameter("toDate");
		String type = request.getParameter("type");
		String make = request.getParameter("make");
		String sort = request.getParameter("sortBy");
		
		//not actually taking dates into consideration yet
		//work on that
		List<Car> cars = carCtrl.getCars();
		
		//get lists of types, makes, and models
		List<String> types = carCtrl.getTypes();
		List<String> makes = carCtrl.getMakes();
		List<String> models = carCtrl.getModels();
		
		//put objects in root map
		root.put("types", types);
		root.put("makes", makes);
		root.put("models", models);
		root.put("cars",cars);
		
		processTemplate("search.ftl");
	}
	
	
	/**
	 * This method receives the information of a new user from the
	 * register.html page. If the username does not exist in the
	 * database, a new entry is made and the user's session is stored. 
	 * If the username does exist, the user is redirected to register.html.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	void register(HttpServletRequest request, HttpServletResponse response){
		//create logic and session objects
		UserLogicImpl userCtrl = new UserLogicImpl();
		HttpSession session = request.getSession();
		
		//gather parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		//if the username already exists, redirect user to register
		if(userCtrl.usernameExists(username)){
			try {
				response.sendRedirect("register.html");//do something with js?
				//tell user what is incorrect in their entry
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			//add entry to db
			userCtrl.register(username, password, firstName, lastName);
			//store user id in session
			session.setAttribute("user", userCtrl.getIdFromUsername(username));
			//redirect user
			sendToLastPage(response, session);
		}
		
	}
	
	/**
	 * This method verifies that the information the user inputs in
	 * login.html is valid. If so, their user id is stored in the
	 * HttpSession and the user is routed to the last page they were
	 * on. If the information is invalid, the user is rerouted to
	 * login.html.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	void login(HttpServletRequest request, HttpServletResponse response){
		//create logic and session objects
		UserLogicImpl userCtrl = new UserLogicImpl();
		HttpSession session = request.getSession();
		
		//gather params
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//validate user - if credentials are valid, route to search
		//if invalid route to login
		if(userCtrl.validate(username, password)){
			session.setAttribute("user", userCtrl.getIdFromUsername(username));
			sendToLastPage(response, session);
		} else{
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method invalidates the user's session and routes
	 * them to index.html.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @param response Used for redirecting the user.
	 */
	void logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		//route user to index.html
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method processes the given FreeMarker template
	 * with the 'root' map.
	 * 
	 * @param templateName The name of the template to be processed.
	 */
	private void processTemplate(String templateName){
		//this method processes the template with the given template name
		try {
			template = cfg.getTemplate(templateName);
			template.process(root, out);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method computes and returns the URL for a given request.
	 * 
	 * @param request Stores the user's request and parameters.
	 * @return URL string
	 */
	private String getURL(HttpServletRequest request) {
	    StringBuffer requestURL = request.getRequestURL();
	    String queryString = request.getQueryString();

	    if (queryString == null) {
	        return requestURL.toString();
	    } else {
	        return requestURL.append('?').append(queryString).toString();
	    }
	}
	
	/**
	 * This method redirects the user to the last page
	 * they visited (stored in HttpSession object).
	 * 
	 * @param response Stores the user's request and parameters.
	 * @param session The current user's session and contents.
	 */
	private void sendToLastPage(HttpServletResponse response, HttpSession session) {
		String url = (String) session.getAttribute("url");
		//send user to previous url if it exists
		//if it does not exist, send user to search
		try {
			if(url!=null)
				response.sendRedirect(url);
			else
				response.sendRedirect("Servlet?req=search");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
