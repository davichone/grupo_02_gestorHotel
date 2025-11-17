package modelo.dao;

import modelo.entidades.Persona;
import java.sql.*;
/**
 *
 * @author alexg
 */
public class PersonaDAO {
    public int insertar(Connection conn, Persona persona) throws SQLException {
        String sql = "INSERT INTO Personas (nombre,tipoDocumento, dni, numeroDocumento,  telefono, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        int personaId = -1;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getTipoDocumento());
            ps.setString(3, persona.getdni());
            ps.setString(4, persona.getNumeroDocumento());
            ps.setString(5, persona.getTelefono());
            ps.setString(6, persona.getEmail());
            // Dirección y fechaRegistro usan valores por defecto/nulos o son ignorados aquí

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        personaId = rs.getInt(1); // Obtiene el ID autogenerado
                        persona.setPersonaID(personaId);
                    }
                }
            }
        }
        return personaId;
    }
}
