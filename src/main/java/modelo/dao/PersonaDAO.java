package modelo.dao;

import modelo.entidades.Persona;
import java.sql.*;
import modelo.dto.PersonaDTO;
/**
 *
 * @author alexg
 */
public class PersonaDAO {
    public int insertar(PersonaDTO persona, Connection conn) throws SQLException {
        String sql = "INSERT INTO Personas (nombre, numeroDocumento, tipoDocumento, telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getNumeroDocumento());
            ps.setString(3, persona.getTipoDocumento());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getEmail());

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
