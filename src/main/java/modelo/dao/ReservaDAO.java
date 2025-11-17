package modelo.dao;

import modelo.dto.ReservaDTO;
import modelo.logica.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar operaciones CRUD en la tabla Reservas.
 */
public class ReservaDAO {

    /**
     * Inserta una nueva reserva en la base de datos.
     * @param reserva La reserva a insertar.
     * @return El ID generado para la reserva insertada.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public int insertar(ReservaDTO reserva) throws SQLException {
        String sql = "INSERT INTO Reservas (clienteID, habitacionNumero, dias, total) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, reserva.getCliente().getClienteID());
            ps.setInt(2, reserva.getHabitacion().getNumero());
            ps.setInt(3, reserva.getDias());
            ps.setDouble(4, reserva.calcularImporteTotal());
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
     * Actualiza una reserva existente.
     * @param reserva La reserva actualizada.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void actualizar(ReservaDTO reserva) throws SQLException {
        String sql = "UPDATE Reservas SET clienteID = ?, habitacionNumero = ?, dias = ?, total = ? WHERE reservaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getCliente().getClienteID());
            ps.setInt(2, reserva.getHabitacion().getNumero());
            ps.setInt(3, reserva.getDias());
            ps.setDouble(4, reserva.calcularImporteTotal());
            ps.setInt(5, reserva.getReservaID());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina una reserva por ID.
     * @param id El ID de la reserva a eliminar.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Reservas WHERE reservaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Busca una reserva por ID.
     * @param id El ID de la reserva.
     * @return La reserva encontrada o null si no existe.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public ReservaDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Reservas WHERE reservaID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Asume inyección de DAOs para cargar Cliente y Habitacion
                    return new ReservaDTO(
                        rs.getInt("reservaID"),
                        null, // Cargar ClienteDTO
                        null, // Cargar HabitacionDTO
                        rs.getInt("dias"),
                        new ArrayList<>() // Servicios adicionales, cargar si hay tabla relacionada
                    );
                }
            }
            return null;
        }
    }

    /**
     * Lista todas las reservas.
     * @return Lista de reservas.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public List<ReservaDTO> listarTodas() throws SQLException {
        List<ReservaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reservas";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ReservaDTO(
                    rs.getInt("reservaID"),
                    null, // Cargar ClienteDTO
                    null, // Cargar HabitacionDTO
                    rs.getInt("dias"),
                    new ArrayList<>()
                ));
            }
        }
        return lista;
    }
}
