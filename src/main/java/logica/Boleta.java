
package logica;
import java.time.LocalDate;
/**
 *
 * @author David Rolando
 */
public class Boleta {
    private Costumer cliente;
    private LocalDate fechaEmision;
    private double precioFinal;
    private ReservaCliente reserva;
    
    public Boleta (Costumer cliente, ReservaCliente reserva){
        this.cliente=cliente;
        this.reserva=reserva;
        this.fechaEmision=LocalDate.now();
        //agregar calculo total de precio
    }
    
    //crear metodo salida boleta en html
    public ReservaCliente getReserva (){
        return reserva;
    }

    public Costumer getCliente() {
        return cliente;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }
    
}
