/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author extru
 */
public class RegistroCliente{
    
    private boolean sauna;
    private boolean garaje;
    private boolean aguaCaliente;
    private String habitacion;
    private double precioHabitacion;
    
public RegistroCliente(boolean sauna, boolean garaje, boolean aguaCaliente, String habitacion) {
    this.sauna = sauna;
    this.garaje = garaje;
    this.aguaCaliente = aguaCaliente;
    this.habitacion = habitacion;
    
    // Limpiamos el texto que llega, sin importar de dónde venga (consola o JComboBox)
    String tipoHabitacionLimpio = habitacion.trim(); 
        if (tipoHabitacionLimpio.equalsIgnoreCase("single room")) {
            this.precioHabitacion = 30.0;
        } else if (tipoHabitacionLimpio.equalsIgnoreCase("double room(separate)") || tipoHabitacionLimpio.equalsIgnoreCase("double room (separate)")) {
            this.precioHabitacion = 60.0;
        } else if (tipoHabitacionLimpio.equalsIgnoreCase("triple room(separate)") || tipoHabitacionLimpio.equalsIgnoreCase("triple room (separate)")) {
            this.precioHabitacion = 90.0;
        } else {
            this.precioHabitacion = 0.0;
        }
    }
   
    public double calcularTotal() {
        double total = precioHabitacion;
        if (sauna) total += 30;
        if (garaje) total += 20;
        if (aguaCaliente) total += 10;
        return total;
    }
    
    public String getHabitacion() {
            return habitacion;  
    }

 }




