package co.jufeng.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreatorMysql {
	
	

	/**
	 * 10 * @param args 11
	 */
	public static void main(String[] args) {
		String database = "jufeng";
		new DatabaseCreatorMysql().getConn(database);
	}

	String mysqlDriver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mysql";
	String newUrl = "jdbc:mysql://localhost:3306/";
	String username = "root";
	String password = "a1234567@!";
	Connection conn = null;
	Connection newConn = null;

	public Connection getConn(String database) {

		try {
			Class.forName(mysqlDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String tableSql = "create table user (user_name varchar(50) not null primary key,"
					+ "password varchar(20) not null ); ";
			String databaseSql = "create database " + database;

			conn = DriverManager.getConnection(url, username, password);
			Statement smt = conn.createStatement();
			if (conn != null) {
				System.out.println("数据库连接成功!");

				smt.executeUpdate(databaseSql);

				newConn = DriverManager.getConnection(newUrl + database,
						username, password);
				if (newConn != null) {
					System.out.println("已经连接到新创建的数据库：" + database);

					Statement newSmt = newConn.createStatement();
					int i = newSmt.executeUpdate(tableSql);// DDL语句返回值为0;
					if (i == 0) {
						System.out.println(tableSql + "表已经创建成功!");
					}
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return conn;
	}
}