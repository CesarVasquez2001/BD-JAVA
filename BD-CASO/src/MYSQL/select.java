package MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class select {

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
			int i = 1;
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cooperativa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Cooperativa " + i++);
				System.out.println("CODIGO: " + rs.getInt("CooCod"));
				System.out.println("CODIGO IDENTIFCADOR: " + rs.getInt("CooIden"));
				System.out.println("NOMBRE: " + rs.getString("CooNom"));
				System.out.println("SIGLAS: " + rs.getString("CooSig"));
				System.out.println("DIRECCION: " + rs.getString("CooDir"));
				System.out.println("TELEFONO: " + rs.getInt("CooTel"));
				System.out.println("CORREO: " + rs.getString("CooCor"));
				System.out.println("SLOGAN: " + rs.getString("CooSlo"));
				System.out.println("LOGO: " + rs.getString("CooLog"));
				System.out.println("CODIGO USUARIO: " + rs.getInt("CooUsu") + "\n");
			}
			rs.close();
			stmt.close();
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