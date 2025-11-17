package modelo.dao;

import modelo.entidades.Usuario;
import java.sql.*;
import modelo.logica.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.UsuarioDTO;

public class UsuarioDAO {
    /**
     * Valida credenciales de usuario
     * @param usuario nombre de usuario
     * @param password contraseña en texto plano
     * @return UsuarioDTO si existe y está activo, null si no
     */
    public UsuarioDTO validarLogin(String usuario, String password, Connection conn) throws SQLException {
        String sql = "SELECT u.*, p.nombre, p.numeroDocumento, r.nombreRol " +
                     "FROM Usuarios u " +
                     "JOIN Personas p ON u.personaID = p.personaID " +
                     "JOIN Roles r ON u.rolID = r.rolID " +
                     "WHERE u.usuario = ? AND u.password_plano = ? AND u.activo = TRUE";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(
                        rs.getInt("usuarioID"),
                        rs.getInt("personaID"),
                        rs.getString("usuario"),
                        rs.getString("password_plano"),
                        rs.getInt("rolID"),
                        rs.getBoolean("activo"),
                        rs.getString("nombre"),
                        rs.getString("numeroDocumento"),
                        rs.getString("nombreRol")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Inserta un nuevo usuario (usado en registro de empleados)
     */
    public int insertar(UsuarioDTO usuario, Connection conn) throws SQLException {
        String sql = "INSERT INTO Usuarios (personaID, usuario, password_plano, rolID, activo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, usuario.getPersonaID());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getRolID());
            ps.setBoolean(5, usuario.isActivo());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Actualiza un usuario
     */
    public void actualizar(UsuarioDTO usuario, Connection conn) throws SQLException {
        String sql = "UPDATE Usuarios SET usuario = ?, password_plano = ?, rolID = ?, activo = ? WHERE usuarioID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getRolID());
            ps.setBoolean(4, usuario.isActivo());
            ps.setInt(5, usuario.getUsuarioID());
            ps.executeUpdate();
        }
    }

    /**
     * Busca usuario por ID
     */
    public UsuarioDTO buscarPorId(int id, Connection conn) throws SQLException {
        String sql = "SELECT u.*, p.nombre, p.numeroDocumento, r.nombreRol " +
                     "FROM Usuarios u " +
                     "JOIN Personas p ON u.personaID = p.personaID " +
                     "JOIN Roles r ON u.rolID = r.rolID " +
                     "WHERE u.usuarioID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    /**
     * Lista todos los usuarios activos
     */
    public List<UsuarioDTO> listarTodos(Connection conn) throws SQLException {
        List<UsuarioDTO> lista = new ArrayList<>();
        String sql = "SELECT u.*, p.nombre, p.numeroDocumento, r.nombreRol " +
                     "FROM Usuarios u " +
                     "JOIN Personas p ON u.personaID = p.personaID " +
                     "JOIN Roles r ON u.rolID = r.rolID " +
                     "WHERE u.activo = TRUE";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    // Mapeo común
    private UsuarioDTO mapearUsuario(ResultSet rs) throws SQLException {
        return new UsuarioDTO(
            rs.getInt("usuarioID"),
            rs.getInt("personaID"),
            rs.getString("usuario"),
            rs.getString("password_plano"),
            rs.getInt("rolID"),
            rs.getBoolean("activo"),
            rs.getString("nombre"),
            rs.getString("numeroDocumento"),
            rs.getString("nombreRol")
        );
    }

}