
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBcity {
	
	private Connection myConn;
	
	//public 
	
	public DBcity() throws Exception {
		
		//variables for database connection
		String user = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/mail";
		
		// establish database connection
		myConn = DriverManager.getConnection(url, user, password);
		
		System.out.println("DB connection successful to:" + url);
	}
	
	private City createCity(ResultSet rows) throws SQLException{
		
		//store required parameters for city instance
		int id = rows.getInt("ID");
		int population = rows.getInt("Population");
		String name = rows.getString("Name");
		String district = rows.getString("District");
		String cc = rows.getString("CountryCode");
		
		//instance of city
		City tempCity = new City(id, name, cc, district, population);
		
		return tempCity;
	}
	
	public List<City> getAllcity() throws Exception {
		
		//instance for ArrayList city
		List<City> list = new ArrayList<>();
		
		Statement sqlState = null;
		ResultSet rows = null;
		
		try
		{
			sqlState = myConn.createStatement();
			rows = sqlState.executeQuery("Select * from city");
			
			while(rows.next())
			{
				City tempCity = createCity(rows);
				list.add(tempCity);
				//System.out.println(tempCity);
			}
			
			return list;
		} 
		
		finally
		{
			close(sqlState, rows);
		}
	}// end of method
	
	private static void close(Connection myConn, Statement sqlState, ResultSet rows) throws SQLException {
		
		if(rows != null){
			rows.close();
		}
		
		if(sqlState != null){
			sqlState.close();
		}
		
		if(myConn !=null){
			myConn.close();
		}
		
	}
	
	private void close(Statement sqlState, ResultSet rows) throws SQLException {
		close(null, sqlState, rows);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		DBcity b = new DBcity();
		b.getAllcity();
		
		City a = new City(1,"Boston","USA","MA",500000);
		System.out.println(a.toString());

	}

}
