package modelo.servicio;

import modelo.dao.SolicitudDAOImpl;
import modelo.dto.SolicitudDTO;
import java.sql.SQLException;
import java.util.List;

public class SolicitudService {
    private final SolicitudDAOImpl dao;

    public SolicitudService() throws SQLException {
        this.dao = new SolicitudDAOImpl();
    }

    public void agregarSolicitud(SolicitudDTO solicitud) throws SQLException, Exception {
        dao.addSolicitud(solicitud);
    }

    public List<SolicitudDTO> obtenerSolicitudes() throws SQLException {
        return dao.getSolicitudes();
    }
}