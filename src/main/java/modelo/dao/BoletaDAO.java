package modelo.dao;

import modelo.dto.BoletaDTO;
import modelo.logica.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar operaciones CRUD en la tabla Facturas (Boletas).
 */
public class BoletaDAO {

    /**
     * Inserta una nueva boleta en la base de datos.
     * @param boleta La boleta a insertar.
     * @return El ID generado para la boleta insertada.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public int insertar(BoletaDTO boleta) throws SQLException {
        String sql = "INSERT INTO Facturas (reservaID, fechaEmision, subTotal, igv, total, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, boleta.getReserva().getReservaID());
            ps.setDate(2, Date.valueOf(boleta.getFechaEmision()));
            ps.setDouble(3, boleta.getSubTotal());
            ps.setDouble(4, boleta.getIgv());
            ps.setDouble(5, boleta.getTotal());
            ps.setString(6, boleta.getEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1; // Error al insertar
        }
    }

    /**
     * Actualiza una boleta existente.
     * @param boleta La boleta actualizada.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void actualizar(BoletaDTO boleta) throws SQLException {
        String sql = "UPDATE Facturas SET reservaID = ?, fechaEmision = ?, subTotal = ?, igv = ?, total = ?, estado = ? WHERE facturaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, boleta.getReserva().getReservaID());
            ps.setDate(2, Date.valueOf(boleta.getFechaEmision()));
            ps.setDouble(3, boleta.getSubTotal());
            ps.setDouble(4, boleta.getIgv());
            ps.setDouble(5, boleta.getTotal());
            ps.setString(6, boleta.getEstado());
            ps.setInt(7, boleta.getFacturaID());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina una boleta por ID.
     * @param id El ID de la boleta a eliminar.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Facturas WHERE facturaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Busca una boleta por ID.
     * @param id El ID de la boleta.
     * @return La boleta encontrada o null si no existe.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public BoletaDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Facturas WHERE facturaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Asume que tienes métodos para obtener ClienteDTO y ReservaDTO por ID
                    // Aquí necesitarías inyectar DAOs de Cliente y Reserva o cargar manualmente
                    // Para simplicidad, placeholder
                    return new BoletaDTO(
                        rs.getInt("facturaID"),
                        null, // Cargar ClienteDTO
                        null, // Cargar ReservaDTO
                        rs.getDate("fechaEmision").toLocalDate(),
                        rs.getDouble("subTotal"),
                        rs.getDouble("igv"),
                        rs.getDouble("total"),
                        rs.getString("estado")
                    );
                }
            }
            return null;
        }
    }

    /**
     * Lista todas las boletas.
     * @return Lista de boletas.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public List<BoletaDTO> listarTodas() throws SQLException {
        List<BoletaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Facturas";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new BoletaDTO(
                    rs.getInt("facturaID"),
                    null, // Cargar ClienteDTO
                    null, // Cargar ReservaDTO
                    rs.getDate("fechaEmision").toLocalDate(),
                    rs.getDouble("subTotal"),
                    rs.getDouble("igv"),
                    rs.getDouble("total"),
                    rs.getString("estado")
                ));
            }
        }
        return lista;
    }
}