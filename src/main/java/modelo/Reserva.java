/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author extru
 */
public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private int dias;

    public Reserva(Cliente cliente, Habitacion habitacion, int dias) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.dias = dias;
    }

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
}
