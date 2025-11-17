package modelo.servicio;

import modelo.dao.ReservaDAO;
import modelo.dao.HabitacionDAO;
import modelo.dto.ReservaDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    public int registrarReserva(ReservaDTO reserva) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();

            // 1. Verificar habitación disponible
            if (habitacionDAO.buscarPorNumero(reserva.getHabitacion().getNumero()).isOcupada()) {
                throw new SQLException("Habitación ya ocupada");
            }

            // 2. Insertar reserva + marcar ocupada (transacción)
            int reservaId = reservaDAO.insertarConTransaccion(reserva, conn);

            ConexionBD.commit(conn);
            return reservaId;

        } catch (SQLException e) {
            ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }

    public void cancelarReserva(int reservaId, int habitacionNumero) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            reservaDAO.eliminar(reservaId, conn);
            habitacionDAO.marcarDisponible(habitacionNumero, conn);
            ConexionBD.commit(conn);
        } catch (SQLException e) {
            ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }

    public List<ReservaDTO> listarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}