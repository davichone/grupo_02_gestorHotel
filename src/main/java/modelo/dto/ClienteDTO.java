package modelo.dto;

import modelo.entidades.Persona;

/**
 * @author extru
 */
public class ClienteDTO extends Persona {
    private int clienteID;

    public ClienteDTO(String nombre, String dni, String telefono) {
        super(nombre, dni, telefono);
    }

    public ClienteDTO(int clienteID, String nombre, String dni, String telefono) {
        super(nombre, dni, telefono);
        this.clienteID = clienteID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    @Override
    public String getRol() {
        return "Cliente";
    }
}
