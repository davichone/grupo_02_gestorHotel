package modelo.logica;

import java.sql.Connection;
import javax.swing.JOptionPane;
import vista.forms.RegistroClienteForm;

public class Login {
Connection con = ConexionBD.conectar();

/*ATRIBUTOS*/
    private String userName;
    private String userPass;
    
    public Login(String userName, String userPass){
        this.userName= userName;
        this.userPass= userPass; 
    }
    
    
    public boolean validarCredenciales() {
        return this.userName.equals("123") && this.userPass.equals("123");
    }
}