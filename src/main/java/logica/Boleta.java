/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    public Boleta (Costumer cliente){
        this.cliente=cliente;
        this.fechaEmision=LocalDate.now();
        //agregar calculo total de precio
    }
    
    
    
    //crear metodo salida boleta en html

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
