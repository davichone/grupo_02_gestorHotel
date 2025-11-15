package modelo.dao;

import modelo.logica.ConexionBD;
import modelo.entidades.Rol;
import java.sql.*;
/**
 *
 * @author alexg
 */
public class RolDAO {
    public int obtenerRolIdPorNombre(String nombreRol) throws SQLException {
        String sql = "SELECT rolID FROM Roles WHERE nombreRol = ?";
        int rolId = -1;
        
        // Usamos getConnection() de tu clase ConexionDB
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, nombreRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rolId = rs.getInt("rolID");
                }
            }
        }
        // Si rolId es -1, significa que el rol no existe.
        return rolId;
    }
}
