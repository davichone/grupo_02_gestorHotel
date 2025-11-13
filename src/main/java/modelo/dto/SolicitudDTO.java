/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author drola
 */
public class SolicitudDTO {
    
    private String detalles;
    private String justificacion;
    private String nroSolicitud, fechaEmision, solicitante, idHotel, gradoUrgencia;
    
    public SolicitudDTO(){}

    public SolicitudDTO(String detalles, String justificacion, String nroSolicitud, String fechaEmision, String solicitante, String idHotel, String gradoUrgencia) {
        this.detalles = detalles;
        this.justificacion = justificacion;
        this.nroSolicitud = nroSolicitud;
        this.fechaEmision = fechaEmision;
        this.solicitante = solicitante;
        this.idHotel = idHotel;
        this.gradoUrgencia = gradoUrgencia;
        
        
        
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(String idHotel) {
        this.idHotel = idHotel;
    }

    public String getGradoUrgencia() {
        return gradoUrgencia;
    }

    public void setGradoUrgencia(String gradoUrgencia) {
        this.gradoUrgencia = gradoUrgencia;
    }
    
    
    
    
}

  