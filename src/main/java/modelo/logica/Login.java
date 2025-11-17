package modelo.logica;

import modelo.dao.UsuarioDAO;
import modelo.dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login {

/*ATRIBUTOS*/
    private String userName;
    private String userPass;
    private UsuarioDTO usuarioLogueado;
    
    public Login(String userName, String userPass){
        this.userName= userName;
        this.userPass= userPass; 
    }
    
    
    public boolean validarCredenciales() {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioLogueado = usuarioDAO.validarLogin(userName, userPass, conn);
            return usuarioLogueado != null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error de base de datos: " + e.getMessage(),
                "Error de Login", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }

    public UsuarioDTO getUsuarioLogueado() {
        return usuarioLogueado;
    }
}