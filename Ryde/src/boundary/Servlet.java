package boundary;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

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
		Writer out = null;									//writer for output
       
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
			case "login":
				//login()
				break;
			case "logout":
				//logout()
				break;
			default:
				break;
			
		}
	}

}
