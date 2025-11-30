/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author drola
 */
public class DataGraficDTO {
    private String name;
    private int edad;
    private int servicioPick;
    private  int satisfaccion;
    private int dias;
    
    public DataGraficDTO (){}

    public DataGraficDTO(String name, int edad, int servicioPick, int satisfaccion, int dias) {
        this.name = name;
        this.edad = edad;
        this.servicioPick = servicioPick;
        this.satisfaccion = satisfaccion;
        this.dias = dias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getServicioPick() {
        return servicioPick;
    }

    public void setServicioPick(int servicioPick) {
        this.servicioPick = servicioPick;
    }

    public int getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(int satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    
    
}
