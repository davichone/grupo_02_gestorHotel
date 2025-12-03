package modelo.servicio;

import modelo.dao.ReservaDAO;
import modelo.dao.HabitacionDAO;
import modelo.dto.ReservaDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.HabitacionDTO;

public class ReservaService {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    public int registrarReserva(ReservaDTO reserva) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            conn.setAutoCommit(false); // IMPORTANTE: Control manual de transacción

            // --- FASE DE BLINDAJE ---
            // Ignoramos el objeto habitacion que viene de la ventana y buscamos el REAL en la BD
            int numeroPuerta = reserva.getHabitacion().getNumero();
            HabitacionDTO habitacionReal = habitacionDAO.buscarPorNumero(numeroPuerta);

            if (habitacionReal == null) {
                throw new SQLException("La habitación " + numeroPuerta + " no existe en la base de datos.");
            }
            if (habitacionReal.isOcupada()) {
                throw new SQLException("La habitación " + numeroPuerta + " ya está ocupada.");
            }
            
            // Reemplazamos en la reserva el objeto incompleto por el real (que tiene el ID correcto)
            reserva.setHabitacion(habitacionReal); 
            // ------------------------

            // Ahora insertamos con seguridad
            int reservaId = reservaDAO.insertarConTransaccion(reserva, conn);
            
            conn.commit(); // Confirmamos cambios
            return reservaId;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            throw e;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
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
        Connection conn = null;
        List<ReservaDTO> lista = new ArrayList<>();
        try {
            // 1. Abrimos la conexión
            conn = ConexionBD.conectar();
            
            // 2. Llamamos al método del DAO que SÍ tiene lógica (el que pide 'conn')
            lista = reservaDAO.listarTodas(conn); 
            
        } catch (SQLException e) {
            System.err.println("Error al listar reservas: " + e.getMessage());
        } finally {
            // 3. Cerramos la conexión para no saturar la base de datos
            ConexionBD.cerrar(conn);
        }
        return lista;
    }
}