package modelo.dto;

public class PersonaDTO {
    private int personaID;
    private String nombre;
    private String numeroDocumento;
    private String tipoDocumento;
    private String telefono;
    private String email;

    // Constructor vacío
    public PersonaDTO() {}

    // Constructor con parámetros (opcional)
    public PersonaDTO(String nombre, String numeroDocumento, String tipoDocumento, 
                      String telefono, String email) {
        this.nombre = nombre;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters
    public int getPersonaID() { return personaID; }
    public void setPersonaID(int personaID) { this.personaID = personaID; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "personaID=" + personaID +
                ", nombre='" + nombre + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}