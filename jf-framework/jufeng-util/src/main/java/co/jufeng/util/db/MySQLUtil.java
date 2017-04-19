package co.jufeng.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLUtil {
	
	public static int cerateDatabases(String userName, String password, String databasesName){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1:3306";
			Connection connection = DriverManager.getConnection(url, userName, password);
			Statement statement = connection.createStatement();
			String hrappSQL = "CREATE DATABASE " + databasesName;
			return statement.executeUpdate(hrappSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
