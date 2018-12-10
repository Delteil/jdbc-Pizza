package utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionPizzeria {

	public static void main(String[] args) {
		
		Connection connect = null;

		try {
			connect = ConnectionPizzeria.getConnection();

			System.out.println("Connexion établie !");

		} catch (Exception e) {
			
		} finally {
			try {
				connect.close();

			} catch (Exception e2) {

			}
		}

	}
	
	public static Connection getConnection() throws Exception {

		Properties prop = new Properties();
		FileInputStream in = new FileInputStream("src/main/java/utils/parameters.properties");
		prop.load(in);
		in.close();

		// Extraction des propriétés
		Class.forName("org.mariadb.jdbc.Driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("dbuser");
		String password = prop.getProperty("dbpassword");

		return DriverManager.getConnection(url, user, password);

	}

}
