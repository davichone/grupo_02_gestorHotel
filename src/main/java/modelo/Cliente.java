package modelo;

/**
 * @author extru
 */
public class Cliente {
    private String nombre;
    private String dni;
    
    public Cliente(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    /**ACA SOLO CLASES DE DATOS DEL CLIENTE**/
    //Getters y Setters
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDni(){
        return dni;
    }
    public void setDni(String dni){
        this.dni = dni;
    } 
}
