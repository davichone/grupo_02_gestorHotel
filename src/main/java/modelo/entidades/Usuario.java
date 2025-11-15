package modelo.entidades;

/**
 *
 * @author alexg
 */
public class Usuario {
    private int usuarioID;
    private String usuario; // Usualmente el DNI o Email
    private int personaID;
    // Campos para seguridad (mantener, aunque usemos plano)
    private byte[] password_salt; 
    private byte[] password_hash; 
    
    private String password_plano; 
    private boolean activo;
    private int rolID;

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPersonaID() {
        return personaID;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public byte[] getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(byte[] password_salt) {
        this.password_salt = password_salt;
    }

    public byte[] getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(byte[] password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_plano() {
        return password_plano;
    }

    public void setPassword_plano(String password_plano) {
        this.password_plano = password_plano;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getRolID() {
        return rolID;
    }

    public void setRolID(int rolID) {
        this.rolID = rolID;
    }
    
    // Constructor para el registro de empleado
    public Usuario(int personaID, String usuario, String passwordPlano, int rolID) {
        this.personaID = personaID;
        this.usuario = usuario;
        this.password_plano = passwordPlano;
        this.rolID = rolID;
        this.activo = true;
    }

    public String getPasswordPlano() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
