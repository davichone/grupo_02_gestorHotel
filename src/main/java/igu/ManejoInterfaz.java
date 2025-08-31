/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package igu;

/**
 *
 * @author extru
 */
public class ManejoInterfaz {
    public ManejoInterfaz(){
        
    }
    
    
    public void opcionInterfaz(int opcion){
        switch (opcion) {
            case 1:         java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true)); // abre menuPrincipal

                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4: java.awt.EventQueue.invokeLater(() -> new LogingUser().setVisible(true));
            
                
                break;    
            default:
                throw new AssertionError();
        }
        
    }
}
