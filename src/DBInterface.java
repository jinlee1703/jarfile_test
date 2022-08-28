import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInterface {
	public static Connection cnn;
	public static Statement stmt;
	
	public static void init() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn=DriverManager.getConnection("jdbc:mysql://localhost/","root","abc123");
		stmt=cnn.createStatement();
		
		System.out.println("DB create");
	}
}
