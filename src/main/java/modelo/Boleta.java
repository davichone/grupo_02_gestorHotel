package modelo;

import java.time.LocalDate;

public class Boleta {
    private Cliente cliente;
    private LocalDate fechaEmision;
    private double precioFinal;
    private Reserva reserva;
    
    public Boleta (Cliente cliente, Reserva reserva){
        this.cliente = cliente;
        this.reserva = reserva;
        this.fechaEmision = LocalDate.now();
        //agregar calculo total de precio
    }
    
    //crear metodo salida boleta en html
    public Reserva getReserva (){
        return reserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }
}
