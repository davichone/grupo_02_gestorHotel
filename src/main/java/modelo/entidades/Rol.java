package modelo.entidades;

/**
 *
 * @author nicol
 */
public class Rol {
    private int rolID;
    private String nombreRol;
    
    // Constructor con ID para usar en lógica
    public Rol(int rolID, String nombreRol) {
        this.rolID = rolID;
        this.nombreRol = nombreRol;
    }

    public int getRolID() { return rolID; }
    public String getNombreRol() { return nombreRol; }
}
