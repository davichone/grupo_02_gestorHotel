package modelo.dto;

import java.time.LocalDate;

public class BoletaDTO {
    private int facturaID;
    private ClienteDTO cliente;
    private ReservaDTO reserva;
    private LocalDate fechaEmision;
    private double subTotal;
    private double igv;
    private double total;
    private String estado; // Por ejemplo: "Emitida", "Pagada", "Anulada"
    
    public BoletaDTO (ClienteDTO cliente, ReservaDTO reserva){
        this.cliente = cliente;
        this.reserva = reserva;
        this.fechaEmision = LocalDate.now();
        this.estado = "Emitida";
        calcularTotales();
    }

    public BoletaDTO (int facturaID, ClienteDTO cliente, ReservaDTO reserva, LocalDate fechaEmision, double subTotal, double igv, double total, String estado){
        this.facturaID = facturaID;
        this.cliente = cliente;
        this.reserva = reserva;
        this.fechaEmision = fechaEmision;
        this.subTotal = subTotal;
        this.igv = igv;
        this.total = total;
        this.estado = estado;
    }

    private void calcularTotales() {
        if (reserva != null) {
            double importeReserva = reserva.calcularImporteTotal();
            this.subTotal = importeReserva;
            this.igv = importeReserva * 0.18; // Asumiendo un IGV del 18%
            this.total = this.subTotal + this.igv;
        } else {
            this.subTotal = 0;
            this.igv = 0;
            this.total = 0;
        }
    }
    
    public int getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(int facturaID) {
        this.facturaID = facturaID;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
        calcularTotales();
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
