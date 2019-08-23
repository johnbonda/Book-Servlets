package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
	private static DBConnector dbConnector;
	private DBConnector() {
		
	}
	
	public Connection getConnection() throws IOException, SQLException {
			Properties properties = DBConnector.getProperties();
			return DriverManager.getConnection(properties.getProperty("url") + properties.getProperty("database") + "?" + "user=" + properties.getProperty("user") + "&password=" + properties.getProperty("password") );
	}
	
	private static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		String propFileName = "C:\\Users\\Abridge Solutions\\eclipse-workspace\\Book Project\\resources\\config.properties";
		File file = new File(propFileName);
		InputStream inputStream = new FileInputStream(file);
		prop.load(inputStream);
		return prop;
	}

	public static DBConnector getConnector() {
		if(dbConnector == null)
			dbConnector = new DBConnector();
		return dbConnector;
	}
}
