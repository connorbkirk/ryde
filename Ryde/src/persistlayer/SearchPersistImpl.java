package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

public class SearchPersistImpl{

    //global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();

    public ResultSet getSuggestions(String col, String searchbarContent){
        ResultSet rs;
		String query = "SELECT DISTINCT "+col+" FROM cars WHERE "+col+" LIKE \'%%" + searchbarContent + "%%\'";
		rs = db.retrieve(con, query);
		return rs;
    }//getSuggestions


}//SearchImpl
