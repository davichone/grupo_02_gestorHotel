package modelo.dto;

public class UsuarioDTO {
    private int usuarioID;
    private int personaID;
    private String usuario;           // login (ej: DNI o email)
    private String password_plano;    // contraseña sin hashear (tu DAO la hashea)
    private int rolID;
    private boolean activo = true;
    
    private String nombrePersona;
    private String numeroDocumento;
    private String nombreRol;

    public UsuarioDTO() {}
    
    public UsuarioDTO(int usuarioID, int personaID, String usuario, 
                      String password_plano, int rolID, boolean activo) {
        this.usuarioID = usuarioID;
        this.personaID = personaID;
        this.usuario = usuario;
        this.password_plano = password_plano;
        this.rolID = rolID;
        this.activo = activo;
    }
    
    public UsuarioDTO(int usuarioID, int personaID, String usuario, 
                      String password_plano, int rolID, boolean activo,
                      String nombrePersona, String numeroDocumento, String nombreRol) {
        this(usuarioID, personaID, usuario, password_plano, rolID, activo);
        this.nombrePersona = nombrePersona;
        this.numeroDocumento = numeroDocumento;
        this.nombreRol = nombreRol;
    }
    public int getUsuarioID() { return usuarioID; }
    public void setUsuarioID(int usuarioID) { this.usuarioID = usuarioID; }

    public int getPersonaID() { return personaID; }
    public void setPersonaID(int personaID) { this.personaID = personaID; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword_plano() { return password_plano; }
    public void setPassword_plano(String password_plano) { this.password_plano = password_plano; }

    public int getRolID() { return rolID; }
    public void setRolID(int rolID) { this.rolID = rolID; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // Campos extra
    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombreRol() { return nombreRol; }
    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "usuarioID=" + usuarioID +
                ", usuario='" + usuario + '\'' +
                ", nombrePersona='" + nombrePersona + '\'' +
                ", nombreRol='" + nombreRol + '\'' +
                ", activo=" + activo +
                '}';
    }

    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}