
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
    private ReservaService reserva;
    
    public Boleta (Costumer cliente){
        this.cliente=cliente;
        this.fechaEmision=LocalDate.now();
        //agregar calculo total de precio
    }
    
    public Boleta(ReservaService reserva){
        this.reserva=reserva;
    }
    
    
    
    //crear metodo salida boleta en html
    public ReservaService getReserva (){
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
