
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
    public void addSolicitud(SolicitudDTO nuevaSolicitud) throws Exception {
        String sql = "INSERT INTO Solicitudes (personaID, fechaEmision, status) VALUES (?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, nuevaSolicitud.getSolicitante());
            pst.setString(2, nuevaSolicitud.getFechaEmision()); // Asumimos "DNI" ya que el form no lo pide
            pst.setString(3, "En revision");

            int filasAfectadas = pst.executeUpdate();
            try {
                if (filasAfectadas > 0) {
                System.out.println("Solicitud guardada en la BD: " + nuevaSolicitud.getNroSolicitud());
                 System.out.println(" ");
            }    
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
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
        List<SolicitudDTO> listaSolicitudes = new ArrayList<>();
        String sql = "SELECT S.solicitudID, P.nombres, S.fechaEmision, S.status FROM Solicitudes AS S INNER JOIN Personas AS P ON S.personaID = P.personaID";
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
                  while (rs.next()) {
                        SolicitudDTO solicitud = new SolicitudDTO();
                        
                        solicitud.setNroSolicitud(String.valueOf(rs.getInt("S.solicitudID")));
                        solicitud.setSolicitante(rs.getString("P.nombres"));
                        solicitud.setFechaEmision( rs.getString("S.fechaEmision"));
                        solicitud.setGradoUrgencia(rs.getString("S.status"));
                        listaSolicitudes.add(solicitud);
                  }
                  System.out.println("Se cargo correctamente");
        }catch(SQLException e){
                System.out.println("error en cargar" +e.getMessage());
        }

        return listaSolicitudes;
    }
}
