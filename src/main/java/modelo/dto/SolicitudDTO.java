package modelo.dto;

import java.time.LocalDate;

public class SolicitudDTO {
    
    private int id; // si usas autoincremental en BD
    private String detalles;
    private String justificacion;
    private String nroSolicitud;
    private String fechaEmision;
    private String solicitante;
    private String idHotel;
    private String gradoUrgencia;
    private String estado;

    public SolicitudDTO() {
        this.fechaEmision = LocalDate.now().toString();
        this.gradoUrgencia = "Medio";
    }

    // Constructor completo
    public SolicitudDTO(String detalles, String justificacion, String nroSolicitud, 
                        String fechaEmision, String solicitante, String idHotel, String gradoUrgencia, String estado) {
        this.detalles = detalles;
        this.justificacion = justificacion;
        this.nroSolicitud = nroSolicitud;
        this.fechaEmision = fechaEmision;
        this.solicitante = solicitante;
        this.idHotel = idHotel;
        this.gradoUrgencia = gradoUrgencia;
        this.estado=estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String detalles) { this.detalles = detalles; }

    public String getJustificacion() { return justificacion; }
    public void setJustificacion(String justificacion) { this.justificacion = justificacion; }

    public String getNroSolicitud() { return nroSolicitud; }
    public void setNroSolicitud(String nroSolicitud) { this.nroSolicitud = nroSolicitud; }

    public String getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }

    public String getIdHotel() { return idHotel; }
    public void setIdHotel(String idHotel) { this.idHotel = idHotel; }

    public String getGradoUrgencia() { return gradoUrgencia; }
    public void setGradoUrgencia(String gradoUrgencia) { this.gradoUrgencia = gradoUrgencia; }
}