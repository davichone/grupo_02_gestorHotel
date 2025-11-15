package modelo.dao;

import modelo.entidades.Usuario;
import java.sql.*;
/**
 *
 * @author alexg
 */
public class UsuarioDAO {
    // Inserta la credencial de usuario usando el personaID obtenido.
    public void insertar(Connection conn, Usuario usuario) throws SQLException {
        // Usamos password_plano para cumplir con la solicitud temporal de guardar en plano
        String sql = "INSERT INTO Usuarios (personaID, usuario, password_plano, rolID, activo) " +
                     "VALUES (?, ?, ?, ?, 1)"; // activo = 1 por defecto

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuario.getPersonaID());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getPasswordPlano()); // GUARDANDO EN PLANO
            ps.setInt(4, usuario.getRolID());

            ps.executeUpdate();
        }
    }
}
