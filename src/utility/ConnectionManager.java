package utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
1.Create a class called as ConnectionManager inside the utility package.
2.Create a method public static Connection getConnection() which returns a connection object.
3.Use the method public static Properties loadPropertiesFile() to load the jdbc properties from the jdbc.properties file.
*/

public class ConnectionManager{
	public static Connection getConnection() throws Exception {
		Properties p=loadPropertiesFile();
		Class.forName(p.getProperty("driver"));
		Connection con=null;
		con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		if(con!=null)
			return con;
		else
			return null;
	}
	
	public static Properties loadPropertiesFile() throws Exception {
		Properties properties=new Properties();
		InputStream in=ConnectionManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
		properties.load(in);
		in.close();
		return properties;
	}
}