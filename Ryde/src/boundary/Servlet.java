package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uga.cs4300.logiclayer.MovieLogicImpl;
import edu.uga.cs4300.objectlayer.Movie;
import edu.uga.cs4300.objectlayer.Review;
import freemarker.template.*;

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
	private SimpleHash root = null;						//root map
	Writer out = null;									//writer for output
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }
    
    public void init() {
    	
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
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//This function processes the request of the user and redirects them to the proper method
		
		response.setContentType("text/html");
		out = response.getWriter();//initialize writer
		root = new SimpleHash(df.build());//initialize root map
		
		//send request to proper destination
		if(request.getParameter("genre")!=null)
			viewTitles(request.getParameter("genre"));
		else if(request.getParameter("id")!=null)
			viewMovie(request.getParameter("id"));
		else if(request.getParameter("addMovie")!=null)
			addMovie(request, response);
		else if(request.getParameter("removeMovie")!=null)
			removeMovie(request, response);
		else if(request.getParameter("addReview")!=null)
			addReview(request, response);
		else if(request.getParameter("editMovie")!=null)
			editMovie(request, response);
		else if(request.getParameter("editReview")!=null)
			editReview(request, response);
		else if(request.getParameter("removeReview")!=null)
			removeReview(request, response);
		else if(request.getParameter("viewReviewId")!=null)
			viewReview(request.getParameter("viewReviewId"));
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
	
	private void viewReview(String id) {
		//This method handles the request of the user wanting to view a review
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//create objects for a movie and review
		Review r = movieCtrl.getReviewById(id);
		Movie m = movieCtrl.getMovieById(r.getMovie_id());
		
		//add movie and review objects to root map
		root.put("movie", m);
		root.put("review", r);
		
		//set templateName and process
		String templateName = "viewReview.ftl";
		processTemplate(templateName);
	}

	private void removeReview(HttpServletRequest request, HttpServletResponse response) {
		//This method handles the request of the user wanting to remove a review
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather parameter
		String id=request.getParameter("removeReviewId");
		
		//if id is not null, remove the review
		if(id!=null){
			movieCtrl.removeReview(id);
			root.put("removeMessage", "removed");
		}
		//if id is null, show a list of reviews
		else{
			List<Review> reviews = movieCtrl.getAllReviews();//get reviews as a list of Review objects
			root.put("reviews", reviews);//place reviews in root map
			root.put("removeMessage", request.getParameter("removeReview"));
		}
		
		//set template name and process
		String templateName = "removeReview.ftl";
		processTemplate(templateName);
	}

	private void editReview(HttpServletRequest request, HttpServletResponse response) {
		//this method handles the request of the user wanting to edit a review
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather parameters
		String id = request.getParameter("edittedReviewId");
		String review=request.getParameter("edittedReview");
		
		//if the id is not null create a movie object for the review and corresponding movie
		if(id!=null){
			//if review is not null, update the existing review
			if(review!=null)
				movieCtrl.editReview(id, review);
			
			Review r = movieCtrl.getReviewById(id);
			Movie m = movieCtrl.getMovieById(r.getMovie_id());
			//place review and movie objects in root map
			root.put("review", r);
			root.put("movie", m);
		}
		root.put("editMessage", request.getParameter("editReview"));
		
		//create template name and process
		String templateName = "editReview.ftl";
		processTemplate(templateName);
	}

	protected void editMovie(HttpServletRequest request, HttpServletResponse response){
		//this method handles the user's request of editing a movie
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather movie parameters
		String id = request.getParameter("editMovieId");
		String name = request.getParameter("edittedMovieName");
		String year = request.getParameter("edittedMovieYear");
		String rank = request.getParameter("edittedMovieRank");
		String[] edittedGenres = request.getParameterValues("edittedMovieGenre");
		
		//if id is not null, create a movie object with the corresponding id
		if(id!=null){
			//if the other movie values are all not null, call the editMovie method to update the row
			if(name!=null && year!=null && rank!=null && edittedGenres!=null)
				movieCtrl.editMovie(id, name, year, rank, edittedGenres);
			Movie movie = movieCtrl.getMovieById(id);
			root.put("movie", movie);
		}
		
		//gather the list of genres and put into root map
		List<String> genres = movieCtrl.getUniqueGenres();
		root.put("genres", genres);
		root.put("editMessage", request.getParameter("editMovie"));
		
		//create template name and process
		String templateName = "editMovie.ftl";
		processTemplate(templateName);
	}
	
	protected void removeMovie(HttpServletRequest request, HttpServletResponse response){
		//the method handles the user's request to remove a movie
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather parameter
		String id = request.getParameter("removeId");
		
		//if id is not null, call the removeMovie method to delete the row
		if(id!=null){
			movieCtrl.removeMovie(id);
			root.put("removeMessage", "removed");
		}
		
		//Gather a list of movie objects and put into root map
		List<Movie> m = movieCtrl.getMovies("All Genres");
		root.put("movies", m);
		root.put("removeMessage", request.getParameter("removeMovie"));
		
		//create template and process
		String templateName = "removeMovie.ftl";
		processTemplate(templateName);
	}
	
	protected void addReview(HttpServletRequest request, HttpServletResponse response){
		//this method handles the user's request to add a review
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather parameters
		String movieId=request.getParameter("addedReviewMovieId");
		String review=request.getParameter("addedReview");
		
		//System.out.println(movieId);
		
		//if the movie id is null, collect a list of movies to use as a drop-down list
		if(movieId==null){
			List<Movie> m = movieCtrl.getMovies("All Genres");
			root.put("movies", m);	
		}
		//if the movie id is not null, create a movie object for the corresponding movie
		else{
			Movie m = movieCtrl.getMovieById(movieId);
			root.put("movie", m);
			
			//if the review is not null, create a new row
			if(review!=null){
				movieCtrl.addReview(movieId, review);
			}
		}
		
		root.put("addMessage", request.getParameter("addReview"));
		
		//create template and process
		String templateName = "addReview.ftl";
		processTemplate(templateName);
	}
	
	protected void addMovie(HttpServletRequest request, HttpServletResponse response){
		//this method handles the user's request to add a movie to the database
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//gather parameters
		String name = request.getParameter("addedMovieName");
		String year = request.getParameter("addedMovieYear");
		String rank = request.getParameter("addedMovieRank");
		String[] genres = request.getParameterValues("addedMovieGenre");
		
		//if name, year, and rank are not null, call the method to add a new movie row
		if(name!=null && year!=null &&rank!=null){
			movieCtrl.addMovie(name, year, rank, genres);
		}
		
		//put all genres into a list of strings and put in the root map
		List<String> allGenres = movieCtrl.getUniqueGenres();
		root.put("genres", allGenres);
		root.put("addMessage", request.getParameter("addMovie"));
		
		//create template and process
		String templateName = "addMovie.ftl";
		processTemplate(templateName);
	}
	
	protected void viewMovie(String id){
		//this method handles the user's request to view a movie
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//create a new movie object for the corresponding id
		Movie m = movieCtrl.getMovieById(id);
		
		//create a list of reviews for the corresponding movie
		List<Review> reviews = movieCtrl.getReviewsByMovieId(id);
		
		//put the movie and list of reviews in the root map
		root.put("movie", m);
		root.put("reviews", reviews);
		
		//create template and process
		String templateName = "viewMovie.ftl";
		processTemplate(templateName);
	}
	
	protected void viewTitles(String genre){
		//this method handles the user's request of viewing titles for a specific genre
		
		MovieLogicImpl movieCtrl = new MovieLogicImpl();
		
		//create a list of all genres
		List<String> genres = movieCtrl.getUniqueGenres();
		
		//create a list of movies for a specific genre
		List<Movie> movies = movieCtrl.getMovies(genre);
		
		//place all genres, the specific genre, and the list of movies in the root map
		root.put("selectedGenre", genre);
		root.put("genres", genres);
		root.put("movies", movies);
		
		//create template and process it
		String templateName = "viewTitles.ftl";
		processTemplate(templateName);
		
	}

}
