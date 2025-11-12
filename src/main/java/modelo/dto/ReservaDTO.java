package modelo.dto;

import modelo.entidades.ServicioAdicional;
import java.util.List;

public class ReservaDTO {

    private int reservaID;
    private ClienteDTO cliente;
    private HabitacionDTO habitacion;
    private int dias;
    private List<ServicioAdicional> servicios;

    public ReservaDTO(ClienteDTO cliente, HabitacionDTO habitacion, int dias, List<ServicioAdicional> servicios) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.dias = dias;
        this.servicios = servicios;
    }
    
    public ReservaDTO(int reservaID, ClienteDTO cliente, HabitacionDTO habitacion, int dias, List<ServicioAdicional> servicios) {
        this.reservaID = reservaID;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.dias = dias;
        this.servicios = servicios;
    }
    
    public double calcularImporteTotal() {
        if (habitacion == null) return 0;
        
        double total = habitacion.getPrecio() * dias;

        for (ServicioAdicional servicio : servicios){
            total += servicio.getPrecio();
        }

        return total;
    }
    
    public int getReservaID() {
        return reservaID;
    }

    public void setReservaID(int reservaID) {
        this.reservaID = reservaID;
    }

    public ClienteDTO getCliente() { 
        return cliente; 
    }
    public void setCliente(ClienteDTO cliente) { 
        this.cliente = cliente; 
    }
    public HabitacionDTO getHabitacion() { 
        return habitacion; 
    }
    public void setHabitacion(HabitacionDTO habitacion) { 
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