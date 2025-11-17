package modelo.dao;

import modelo.dto.HabitacionDTO;
import modelo.logica.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar operaciones CRUD en la tabla Habitaciones.
 */
public class HabitacionDAO {

    /**
     * Inserta una nueva habitación en la base de datos.
     * @param habitacion La habitación a insertar.
     * @return El ID generado (si aplica).
     * @throws SQLException Si ocurre un error en la BD.
     */
    public int insertar(HabitacionDTO habitacion) throws SQLException {
        String sql = "INSERT INTO Habitaciones (numero, tipo, precio, estaOcupada, idTipoHabitacion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getTipo());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setBoolean(4, habitacion.isOcupada());
            ps.setInt(5, habitacion.getIdTipoHabitacion());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
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
     * Actualiza una habitación existente.
     * @param habitacion La habitación actualizada.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void actualizar(HabitacionDTO habitacion) throws SQLException {
        String sql = "UPDATE Habitaciones SET tipo = ?, precio = ?, estaOcupada = ?, idTipoHabitacion = ? WHERE numero = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, habitacion.getTipo());
            ps.setDouble(2, habitacion.getPrecio());
            ps.setBoolean(3, habitacion.isOcupada());
            ps.setInt(4, habitacion.getIdTipoHabitacion());
            ps.setInt(5, habitacion.getNumero());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina una habitación por número.
     * @param numero El número de la habitación a eliminar.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void eliminar(int numero) throws SQLException {
        String sql = "DELETE FROM Habitaciones WHERE numero = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            ps.executeUpdate();
        }
    }

    /**
     * Busca una habitación por número.
     * @param numero El número de la habitación.
     * @return La habitación encontrada o null si no existe.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public HabitacionDTO buscarPorNumero(int numero) throws SQLException {
        String sql = "SELECT * FROM Habitaciones WHERE numero = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HabitacionDTO(
                        rs.getInt("numero"),
                        rs.getString("tipo"),
                        rs.getDouble("precio"),
                        rs.getBoolean("estaOcupada"),
                        rs.getInt("idTipoHabitacion")
                    );
                }
            }
            return null;
        }
    }

    /**
     * Lista todas las habitaciones.
     * @return Lista de habitaciones.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public List<HabitacionDTO> listarTodas() throws SQLException {
        List<HabitacionDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Habitaciones";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new HabitacionDTO(
                    rs.getInt("numero"),
                    rs.getString("tipo"),
                    rs.getDouble("precio"),
                    rs.getBoolean("estaOcupada"),
                    rs.getInt("idTipoHabitacion")
                ));
            }
        }
        return lista;
    }
    
    public void marcarOcupada(int numero, Connection conn) throws SQLException {
        String sql = "UPDATE Habitaciones SET estaOcupada = TRUE WHERE numero = ? AND estaOcupada = FALSE";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Habitación no disponible: " + numero);
            }
        }
    }

    public void marcarDisponible(int numero, Connection conn) throws SQLException {
        String sql = "UPDATE Habitaciones SET estaOcupada = FALSE WHERE numero = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            ps.executeUpdate();
        }
    }

    HabitacionDTO buscarPorNumero(int aInt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}