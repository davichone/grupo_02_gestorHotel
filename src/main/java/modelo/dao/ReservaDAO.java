package modelo.dao;

import modelo.dto.ReservaDTO;
import modelo.dto.HabitacionDTO;
import modelo.dto.ClienteDTO;
import java.sql.*;
import modelo.logica.ConexionBD;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // === INSERTAR RESERVA + MARCAR HABITACIÓN COMO OCUPADA (TRANSACCIÓN) ===
    public int insertarConTransaccion(ReservaDTO reserva, Connection conn) throws SQLException {
        // CORRECCIÓN: Usamos 'estado' en lugar de 'estaOcupada'
        // CORRECCIÓN: Agregamos CURDATE() para las fechas obligatorias si no las tienes en el DTO
        String sqlReserva = "INSERT INTO reservas (clienteID, habitacionID, fechaEntrada, fechaSalida, dias, importeTotal, estado) " +
                            "VALUES (?, ?, CURDATE(), CURDATE(), ?, ?, 'Confirmada')";
        
        String sqlUpdateHab = "UPDATE habitaciones SET estado = 'Ocupada' WHERE habitacionID = ? AND estado = 'Disponible'";

        PreparedStatement psReserva = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        try {
            // 1. Actualizar habitación (Marcar como Ocupada) usando el ID Interno
            psUpdate = conn.prepareStatement(sqlUpdateHab);
            psUpdate.setInt(1, reserva.getHabitacion().getHabitacionID()); 
            
            int updated = psUpdate.executeUpdate();

            if (updated == 0) {
                throw new SQLException("La habitación (ID: " + reserva.getHabitacion().getHabitacionID() + ") ya está ocupada o no existe.");
            }

            // 2. Insertar reserva
            psReserva = conn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);
            psReserva.setInt(1, reserva.getCliente().getClienteID());
            psReserva.setInt(2, reserva.getHabitacion().getHabitacionID()); // Usamos ID, no numero
            psReserva.setInt(3, reserva.getDias());
            psReserva.setDouble(4, reserva.calcularImporteTotal());

            int filas = psReserva.executeUpdate();
            if (filas > 0 && (rs = psReserva.getGeneratedKeys()).next()) {
                return rs.getInt(1);
            }
            throw new SQLException("No se pudo insertar la reserva, no se generó ID.");

        } finally {
            if (rs != null) rs.close();
            if (psReserva != null) psReserva.close();
            if (psUpdate != null) psUpdate.close();
        }
    }

    // === MÉTODOS INDIVIDUALES (sin transacción propia) ===
    public int insertar(ReservaDTO reserva, Connection conn) throws SQLException {
        String sql = "INSERT INTO Reservas (clienteID, habitacionNumero, dias, total) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, reserva.getCliente().getClienteID());
            ps.setInt(2, reserva.getHabitacion().getNumero());
            ps.setInt(3, reserva.getDias());
            ps.setDouble(4, reserva.calcularImporteTotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void actualizar(ReservaDTO reserva, Connection conn) throws SQLException {
        String sql = "UPDATE Reservas SET clienteID = ?, habitacionNumero = ?, dias = ?, total = ? WHERE reservaID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reserva.getCliente().getClienteID());
            ps.setInt(2, reserva.getHabitacion().getNumero());
            ps.setInt(3, reserva.getDias());
            ps.setDouble(4, reserva.calcularImporteTotal());
            ps.setInt(5, reserva.getReservaID());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM Reservas WHERE reservaID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ReservaDTO buscarPorId(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM Reservas WHERE reservaID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    HabitacionDAO habitacionDAO = new HabitacionDAO();
                    ClienteDTO cliente = clienteDAO.buscarPorId(rs.getInt("clienteID"), conn);
                    HabitacionDTO habitacion = habitacionDAO.buscarPorNumero(rs.getInt("habitacionNumero"), conn);

                    return new ReservaDTO(
                        rs.getInt("reservaID"),
                        cliente,
                        habitacion,
                        rs.getInt("dias"),
                        new ArrayList<>()
                    );
                }
            }
        }
        return null;
    }

    public List<ReservaDTO> listarTodas(Connection conn) throws SQLException {
        List<ReservaDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reservas";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ClienteDAO clienteDAO = new ClienteDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            while (rs.next()) {
                ClienteDTO cliente = clienteDAO.buscarPorId(rs.getInt("clienteID"), conn);
                HabitacionDTO habitacion = habitacionDAO.buscarPorNumero(rs.getInt("habitacionNumero"), conn);
                lista.add(new ReservaDTO(
                    rs.getInt("reservaID"),
                    cliente,
                    habitacion,
                    rs.getInt("dias"),
                    new ArrayList<>()
                ));
            }
        }
        return lista;
    }

    public List<ReservaDTO> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}