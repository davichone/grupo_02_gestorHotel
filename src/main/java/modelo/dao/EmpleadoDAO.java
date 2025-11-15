package modelo.dao;

import modelo.entidades.Empleado;
import java.sql.*;

public class EmpleadoDAO {
    public void insertar(Connection conn, Empleado empleado) throws SQLException {
        String sql = "INSERT INTO Empleados (personaID, fechaContratacion, salario, activo) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empleado.getPersonaID());
            ps.setDate(2, Date.valueOf(empleado.getFechaContratacion()));
            ps.setDouble(3, empleado.getSalario());
            ps.setBoolean(4, empleado.isActivo());

            ps.executeUpdate();
        }
    }
}
