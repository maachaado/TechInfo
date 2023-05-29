package br.com.techinfo2.core.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {

	private static final String urlDb = "jdbc:mysql://localhost:3306/tech_info";
	private static final String userDb = "root";
	private static final String passDb = "root";
	
	private static Connection conn;
	
	public static Connection getConexao( ) {
	
	try {
		if(conn == null) {
				conn = DriverManager.getConnection(urlDb, userDb, passDb);
				return conn;
		}else {
			return conn;
		}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}		
		}
	}
	
//	public static void main(String[] args) {
//		
//		try {
//			Connection con = DriverManager.getConnection(urlDb, userDb, passDb);
//			Statement stmt = con.createStatement(); 
//			ResultSet rs = stmt.executeQuery("SELECT NM_CLIENTE FROM cliente");
//			while(rs.next()) {
//				System.out.println(rs.getString("NM_CLIENTE"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	

