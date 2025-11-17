package modelo.dto;

public class UsuarioDTO {
    private int usuarioID;
    private int personaID;
    private String usuario;
    private String password;
    private int rolID;
    private boolean activo;
    private String nombrePersona;      // Nuevo
    private String documentoPersona;   // Nuevo
    private String nombreRol;          // Nuevo

    // Constructor completo
    public UsuarioDTO(int usuarioID, int personaID, String usuario, String password,
                      int rolID, boolean activo, String nombrePersona,
                      String documentoPersona, String nombreRol) {
        this.usuarioID = usuarioID;
        this.personaID = personaID;
        this.usuario = usuario;
        this.password = password;
        this.rolID = rolID;
        this.activo = activo;
        this.nombrePersona = nombrePersona;
        this.documentoPersona = documentoPersona;
        this.nombreRol = nombreRol;
    }

    // Constructor sin ID (para insertar)
    public UsuarioDTO(int personaID, String usuario, String password, int rolID) {
        this.personaID = personaID;
        this.usuario = usuario;
        this.password = password;
        this.rolID = rolID;
        this.activo = true;
    }

    // Getters y Setters
    public int getUsuarioID() { return usuarioID; }
    public void setUsuarioID(int usuarioID) { this.usuarioID = usuarioID; }

    public int getPersonaID() { return personaID; }
    public void setPersonaID(int personaID) { this.personaID = personaID; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRolID() { return rolID; }
    public void setRolID(int rolID) { this.rolID = rolID; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getDocumentoPersona() { return documentoPersona; }
    public void setDocumentoPersona(String documentoPersona) { this.documentoPersona = documentoPersona; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }
}