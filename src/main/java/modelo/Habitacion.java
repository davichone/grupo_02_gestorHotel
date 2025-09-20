/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author extru
 */
public class Habitacion {
    private int numero;
    private String tipo; //para la habitacion sea esta simple, doble, suite
    private boolean disponible;
    
    public Habitacion(int numero, String tipo, boolean disponible){
        this.numero = numero;
        this.tipo = tipo;
        this.disponible = disponible;
    }
    
    public int getNumero(){
        return numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    
    public String getTipo(){
        return tipo;
    }
    public void setTipo(){
        this.tipo = tipo;
    }
    
    public boolean isDisponible(){
        return disponible;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    
}
