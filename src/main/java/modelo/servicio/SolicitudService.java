package modelo.servicio;

import modelo.dao.SolicitudDAO;
import modelo.dto.SolicitudDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import modelo.dao.SolicitudDAOImpl;

public class SolicitudService {
    private SolicitudDAO solicitudDAO = new SolicitudDAOImpl();

    public void agregarSolicitud(SolicitudDTO solicitud) throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            solicitudDAO.addSolicitud(solicitud);
            ConexionBD.commit(conn);
        } catch (SQLException e) {
            ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }

    public List<SolicitudDTO> obtenerSolicitudes() throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            return solicitudDAO.getSolicitudes();
        } finally {
            ConexionBD.cerrar(conn);
        }
    }
}
