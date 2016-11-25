//TODO
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

import persistlayer.SearchPersistImpl;

public class SearchLogicImpl{

    private SearchPersistImpl sp;

    public SearchLogicImpl(){
	sp = new SearchPersistImpl();
    }

    public List<String> getSuggestionsFromPersist(String currentSearchBarString){
	ResultSet rs = null;
	rs = sp.getSuggestions(currentSearchBarString);

	List<String>resultMakes = new ArrayList<String>();

	try 
	{
	    while(rs.next())
	    {
		String make = rs.getString("make");
   

		resultMakes.add(make);
		//dynamically creates strings for makes.                                                                                                                                          
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block                                                                                                                                                  
	    e.printStackTrace();
	}

	return resultCars;
    }


}
