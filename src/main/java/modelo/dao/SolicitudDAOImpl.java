
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.dto.ClienteDTO;
import modelo.dto.SolicitudDTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import modelo.logica.ConexionBD;

/**
 *
 * @author drola
 */
public class SolicitudDAOImpl implements SolicitudDAO {
     private final Connection con;

    public SolicitudDAOImpl() throws SQLException {
         this.con = ConexionBD.conectar();
    }
    
    
     @Override
    public void generarSolicitud(SolicitudDTO nuevaSolicitud) throws Exception {
        
    }

    @Override
    public void addSolicitud(SolicitudDTO solicitud) throws SQLException {
    String sql = "INSERT INTO Solicitudes (detalles, justificacion, fechaEmision, solicitante, idHotel, gradoUrgencia) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, solicitud.getDetalles());
        ps.setString(2, solicitud.getJustificacion());
        ps.setString(3, solicitud.getFechaEmision());
        ps.setString(4, solicitud.getSolicitante());
        ps.setString(5, solicitud.getIdHotel());
        ps.setString(6, solicitud.getGradoUrgencia());
        ps.executeUpdate();
    }
}
    
    @Override
    public void cargarSolicitudes() throws Exception {
         String sql = "SELECT solicitudID, personaID, fechaEmision FROM Solicitudes";
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
//            while (rs.next()) {
                String solicitudID = String.valueOf(rs.getInt("solicitudID")) ;
                String personaID = rs.getString("personaID");
                String fechaEmision = rs.getString("fechaEmision");
                String status = rs.getString("status");
//            }
            System.out.println("Se cargaron las solicitudes de la dataBase");
            System.out.println(" ");
            pst.setString(1, solicitudID);
            pst.setString(2, personaID); // Asumimos "DNI" ya que el form no lo pide
            pst.setString(3,fechaEmision );
            pst.setString(4,status );
           
            int filasAfectadas = pst.executeUpdate();
            try {
                if (filasAfectadas > 0) {
                System.out.println("Solicitud guardada en la BD: " );
                 System.out.println(" ");
            }    
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error fatal al cargar solicitudes: " + e.getMessage());
             System.out.println(" ");
        } 
    }

    @Override
    public List<SolicitudDTO> getSolicitudes() throws SQLException {
    List<SolicitudDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM Solicitudes ORDER BY fechaEmision DESC";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            SolicitudDTO s = new SolicitudDTO();
            s.setDetalles(rs.getString("detalles"));
            s.setJustificacion(rs.getString("justificacion"));
            s.setFechaEmision(rs.getString("fechaEmision"));
            s.setSolicitante(rs.getString("solicitante"));
            s.setGradoUrgencia(rs.getString("gradoUrgencia"));
            lista.add(s);
        }
    }
        return lista;
    }
}
