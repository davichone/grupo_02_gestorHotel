package modelo;

import interfaces.ServicioAdicional;
import java.util.List;

public class Reserva {

    private Cliente cliente;
    private Habitacion habitacion;
    private int dias;
    private List<ServicioAdicional> servicios;

    public Reserva(Cliente cliente, Habitacion habitacion, int dias, List<ServicioAdicional> servicios) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.dias = dias;
        this.servicios = servicios;
    }
    
    // MÉTODO DE CÁLCULO ACTUALIZADO
    public double calcularImporteTotal() {
        if (habitacion == null) return 0;
        
        double total = habitacion.getPrecio() * dias;

        // El 'for-each' funciona perfectamente con una List.
        for (ServicioAdicional servicio : servicios){
            total += servicio.getPrecio();
        }

        return total;
    }
    
    // --- Getters y Setters
    public Cliente getCliente() { 
        return cliente; 
    }
    public void setCliente(Cliente cliente) { 
        this.cliente = cliente; 
    }
    public Habitacion getHabitacion() { 
        return habitacion; 
    }
    public void setHabitacion(Habitacion habitacion) { 
        this.habitacion = habitacion; 
    }
    public int getDias() { 
        return dias; 
    }
    public void setDias(int dias) { 
        this.dias = dias; 
    }

    public List<ServicioAdicional> getServiciosAdicionales(){
        return servicios;
    }
}