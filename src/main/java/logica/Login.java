package logica;

import javax.swing.JOptionPane;
import vista.CustomerRegistration;


public class Login {

/*ATRIBUTOS*/
    private String userName;
    private String userPass;
    private int intentos;
    private static final int MAX_INTENTOS = 3;
    
    public Login(String userName, String userPass){
        this.userName= userName;
        this.userPass= userPass; 
        this.intentos = 0;
    }
    
    public void loginUser(){
        
        intentos++;
      
        if (intentos > MAX_INTENTOS) {
            JOptionPane.showMessageDialog(null,
                    "Limite de intentos alcanzados, vuelve mas tarde");
            return;
          } 
        
       
          if(!this.userName.equals("cliente")) {
          JOptionPane.showMessageDialog(null,
                    "Usuario incorrecta intentos:"+ intentos);
         
            return;
          }  
          
           if(!this.userPass.equals("123")) {
                
            JOptionPane.showMessageDialog(null,
                    "Contraseña incorrecta");
            return;
          } 
           
          /*REDIRECCION*/ 
          java.awt.EventQueue.invokeLater(() -> new CustomerRegistration().setVisible(true));
         
          
    }
    
    
    
}
