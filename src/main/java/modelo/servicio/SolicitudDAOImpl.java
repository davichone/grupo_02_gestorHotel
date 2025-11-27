
package modelo.servicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.dto.SolicitudDTO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.SolicitudDAO;
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
    String sql = "INSERT INTO solicitudes (detalles, justificacion, fechaEmision, solicitante, idHotel ,gradoUrgencia,estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
       
        ps.setString(1, solicitud.getDetalles());
        ps.setString(2, solicitud.getJustificacion());
        ps.setString(3, solicitud.getFechaEmision());
        ps.setString(4, solicitud.getSolicitante());
        ps.setString(5, solicitud.getIdHotel());
        ps.setString(6, solicitud.getGradoUrgencia());
        ps.setString(7, solicitud.getEstado());
        ps.executeUpdate();
        conn.commit();
        System.out.println("------Solicitud guardada en la base datos");
    }catch(SQLException e){
        System.out.println("Error en guardar la solicitud en BD"+ e);
    }
}
    
    

    @Override
    public List<SolicitudDTO> getListaDeSolicitudesParaTabla() throws SQLException {
    List<SolicitudDTO> lista = new ArrayList<>();
    String sql = "SELECT solicitudID, idHotel,  solicitante, fechaEmision, gradoUrgencia, estado FROM Solicitudes";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement ps = conn.prepareStatement(sql);     //try with resources , se cierra la conexion una ves terminada la peticion ga
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            SolicitudDTO objetoSolicitud = new SolicitudDTO();
                objetoSolicitud.setNroSolicitud(rs.getString("solicitudID"));
                objetoSolicitud.setIdHotel(rs.getString("idHotel"));
                objetoSolicitud.setSolicitante(rs.getString("solicitante"));        //pasando valores a atributos del objeto
                objetoSolicitud.setFechaEmision(rs.getString("fechaEmision"));
                objetoSolicitud.setGradoUrgencia(rs.getString("gradoUrgencia"));
                objetoSolicitud.setEstado(rs.getString("estado"));
               lista.add(objetoSolicitud);  //objeto con valores listos es agregado al arraylist
        }
    }
        return lista;
    }
    
    
    @Override
   public String getNroSolicitud() throws SQLException{
            String sql = "SELECT solicitudID FROM solicitudes ORDER BY solicitudID DESC LIMIT 1";
            String numero = " "; //porsiaca xd
            
        try (Connection conn = ConexionBD.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){        
            rs.next();
             numero =rs.getString("solicitudID");
        }    
        return  numero;
    }
}
