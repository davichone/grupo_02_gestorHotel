package modelo;

/**
 * @author extru
 */
public class Cliente {
    private String nombre;
    private String dni;
   private String telefono;
    
    public Cliente(String nombre, String dni,String telefono){
        this.nombre = nombre;
        this.dni = dni;
        this.telefono=telefono;
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
    
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
}
