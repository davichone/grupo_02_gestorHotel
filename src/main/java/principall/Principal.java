
package principall;


import vista.LoginUsuario;

public class Principal {

    public static void main(String[] args) {
        
     java.awt.EventQueue.invokeLater(() -> new LoginUsuario().setVisible(true));
        
    }
}