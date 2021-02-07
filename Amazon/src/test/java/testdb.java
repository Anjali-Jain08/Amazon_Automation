import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.testng.annotations.Test;

//import testscripts.Amazon_DriverScript;

public class testdb{
	
	
	public static void dbConnect() throws ClassNotFoundException, SQLException {

		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Conection established");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazon","root","_Anjali@jain08");
		System.out.println("Connected to DB");
		
		Statement stmnt = con.createStatement();
		ResultSet result = stmnt.executeQuery("select * from added_product");

		while(result.next()) {
			
			String productName = result.getString("product_name");
			System.out.println("first record is " + productName);
		}
		//System.out.println(result);
				
	}

}
