/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.SQLException;
import java.util.List;
import modelo.dto.SolicitudDTO;

/**
 *
 * @author drola
 */
public interface SolicitudDAO {
    
    
    void generarSolicitud(SolicitudDTO nuevaSolicitud) throws Exception;
    void addSolicitud(SolicitudDTO nuevaSolicitud) throws Exception;
    void cargarSolicitudes() throws Exception;
    List<SolicitudDTO> getSolicitudes() throws SQLException;
    
    
    
}
