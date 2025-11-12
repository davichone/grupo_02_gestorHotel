
package modelo.logica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Conexion exitosa con la base de datos");
            System.out.println(" ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" Error al conectar: " + e.getMessage());
            System.out.println(" ");
        }
        return con;
    }
}
    

