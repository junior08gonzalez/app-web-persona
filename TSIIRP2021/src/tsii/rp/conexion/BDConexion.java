package tsii.rp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {

	public static Connection obtenerConexion()
	{
		String url = "jdbc:sqlserver://localhost:1433; databaseName=ts2rp2021; username=satsi2; password=sa2021";
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
		}catch(SQLException | ClassNotFoundException e) {
			System.out.println("Error al obtener una conexion a la BD, "+ e.getMessage());
		}
		return con;
	}
}
