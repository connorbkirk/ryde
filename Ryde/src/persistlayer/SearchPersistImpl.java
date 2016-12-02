package persistlayer;

import java.sql.Connection;
import java.sql.ResultSet;

public class SearchPersistImpl{

    //global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();

    public ResultSet getSuggestions(String searchbarContent){
        ResultSet rs;
		String query = "SELECT DISTINCT makes FROM cars WHERE make LIKE \'%%" + searchbarContent + "%%\' LIMIT 1";
		rs = db.retrieve(con, query);
		return rs;
    }//getSuggestions


}//SearchImpl
