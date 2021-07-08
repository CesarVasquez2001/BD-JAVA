package MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class conexion {
	public static void main(String[] args) {
		Connection con = null;
		final String url = "jdbc:mysql://localhost:3306/";
		final String dbName = "caso-cooperativa";
		final String username = "root";
		final String password = "2202001";
		try {
			con = (Connection) DriverManager.getConnection(url + dbName, username, password);
			if (!con.isClosed())
				System.out.println("La base datos esta conectada");
		} catch (Exception e) {
			System.err.print("Exception: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) {
			}
		}
	}
}
