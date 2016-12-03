//TODO
package logiclayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistlayer.SearchPersistImpl;

public class SearchLogicImpl{

    private SearchPersistImpl sp;

    public SearchLogicImpl(){
	sp = new SearchPersistImpl();
    }


	public List<String> getMakes(String currentSearchBarContent) {
		ResultSet rs = null;
		rs = sp.getSuggestions("make", currentSearchBarContent);
	
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
		return resultMakes;
	}


	public List<String> getTypes(String currentSearchBarContent) {
		ResultSet rs = null;
		rs = sp.getSuggestions("carType", currentSearchBarContent);
	
		List<String>resultTypes = new ArrayList<String>();
		try 
		{
		    while(rs.next())
		    {
			String type = rs.getString("carType");
	
			resultTypes.add(type);
			//dynamically creates strings for makes.                                                                                                                                          
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block                                                                                                                                                  
		    e.printStackTrace();
		}
		return resultTypes;
	}


	public List<String> getModels(String currentSearchBarContent) {
		ResultSet rs = null;
		rs = sp.getSuggestions("model", currentSearchBarContent);
	
		List<String>resultTypes = new ArrayList<String>();
		try 
		{
		    while(rs.next())
		    {
			String type = rs.getString("model");
	
			resultTypes.add(type);
			//dynamically creates strings for makes.                                                                                                                                          
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block                                                                                                                                                  
		    e.printStackTrace();
		}
		return resultTypes;
	}


}
