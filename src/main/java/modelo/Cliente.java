package modelo;

/**
 * @author extru
 */
public class Cliente extends Persona {

    public Cliente(String nombre, String dni, String telefono) {
        // Llama al constructor de la clase padre (Persona)
        super(nombre, dni, telefono);
    }

    @Override
    public String getRol() {
        return "Cliente";
    }
}
