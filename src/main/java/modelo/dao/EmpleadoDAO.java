package modelo.dao;

import modelo.entidades.Empleado;
import java.sql.*;
import modelo.dto.EmpleadoDTO;

public class EmpleadoDAO {
    public int insertar(EmpleadoDTO empleado, Connection conn) throws SQLException {
        String sql = "INSERT INTO Empleados (personaID) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, empleado.getPersonaID());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
            return -1;
        }
    }
}
