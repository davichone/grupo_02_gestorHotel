package logica;

import javax.swing.JOptionPane;
import vista.CustomerRegistration;

public class Login {

/*ATRIBUTOS*/
    private String userName;
    private String userPass;
    
    public Login(String userName, String userPass){
        this.userName= userName;
        this.userPass= userPass; 
    }
    
    public boolean validarCredenciales() {
        return this.userName.equals("cliente") && this.userPass.equals("123");
    }
}