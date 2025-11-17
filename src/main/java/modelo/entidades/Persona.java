package modelo.entidades;

import java.time.LocalDateTime;
/**
 *
 * @author alexg
 */
public abstract class Persona{
    protected int personaID;
    protected String nombre;
    protected String telefono;
    protected String dni;
    protected String numeroDocumento;
    protected String email;
    protected LocalDateTime fechaRegistro;
    
    public Persona() {
    }

    public Persona(String nombre, String dni, String apellidos, String tipoDocumento, String numeroDocumento, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.numeroDocumento = dni;
        this.email = email;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public String getdni() {
        return dni;
    }

    public void setdni(String dni) {
        this.dni = dni;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Getters comunes para todas las personas
    public String getNombre() { return nombre; }
    public String getDni() { return numeroDocumento; }
    public String getTelefono() { return telefono; }

    // MÉTODO ABSTRACTO: Obliga a todas las clases hijas a implementar este método,
    // diciendo qué rol cumplen en el sistema.
    public abstract String getRol();

    public String dni() {
        return this.dni;
    }

    public String getTipoDocumento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
