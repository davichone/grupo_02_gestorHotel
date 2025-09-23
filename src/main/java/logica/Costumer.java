/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

public class Costumer {
  
    private String nombre, telefono, dni, email;
    private int edad;
    
     public Costumer(String nombre,String telefono, String dni){
        this.dni = dni;
        this.nombre = nombre;
        this.telefono=telefono;
        this.email=email; //añadir formulario
 
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }
     
     
     //metodo para visualizar todos los datos del cliente (prueba en consola)
     @Override
     public String toString (){
         
         return "CLiente\n"+"Nombre: "+nombre+"\nTelefono: "+telefono+"\nDni: "+dni;
         
        
        
     
     }
    
}
