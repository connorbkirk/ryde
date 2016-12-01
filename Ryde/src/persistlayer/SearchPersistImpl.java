package persistlayer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SearchImpl{

    //global variables
	DbAccessImpl db = new DbAccessImpl();
	Connection con = db.connect();

    public ResultSet getSuggestions(String searchbarContent){
        ResultSet rs;
		String query = "SELECT * FROM cars WHERE make LIKE "+ "%%\"" + searchbarContent + "\"%% LIMIT 1";
		rs = db.retrieve(con, query);
		return rs;
    }//getSuggestions


}//SearchImpl
