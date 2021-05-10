package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class DbConnection {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String HOST = "localhost";
	//private static final int PORT = 3304;
	private static final String DB_NAME = "gestion_pfe";
	private static Statement st = null;
	public static Connection con = null;
	
	public DbConnection() {
		createConnection();
	}
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB_NAME ,USERNAME, PASSWORD);
			return con ;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null ;
	}
	
	
	/*static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB_NAME ,USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	//chekLogin if ==1 pass or user doesn't existe in db if ==0 Ok if ==-1 db not connected 
	/*public static int checkLogin(String username , String password) {
		Connection con = DbConnection.con;
		if (con == null) return -1;
		
		
		String sql = "SELECT * FROM prof  WHERE username=? AND password=?";
		try {
			PreparedStatement prest = (PreparedStatement) con.prepareStatement(sql);
			prest.setString(1, username);
			prest.setString(2, password);
			
			ResultSet rs = prest.executeQuery();
			
			while (rs.next()) {
				return 0 ;
			}
			
			
			
			
		} catch (SQLException se) {
			se.printStackTrace();	
			
		}
		
		String sql2 = "SELECT * FROM etudiant  WHERE username=? AND password=?";
		try {
			PreparedStatement prest2 = (PreparedStatement) con.prepareStatement(sql2);
			prest2.setString(1, username);
			prest2.setString(2, password);
			
			ResultSet rs2 = prest2.executeQuery();
			
			while (rs2.next()) {
				return 0 ;
			}
			
			
			
			
		} catch (SQLException se) {
			se.printStackTrace();	
			
		}
		return 1; 
		
	}*/
	
	public static ResultSet executeQuery(String query, Connection con) {
        ResultSet result;
        try {
            st = con.createStatement();
            result = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at executeQuery:DbConnection" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
	
	
	
}
