package modelo.dto;

import modelo.entidades.Persona;

/**
 * @author extru
 */
public class ClienteDTO extends Persona {
    private int clienteID;

    public ClienteDTO(String nombre, String numeroDocumento, String telefono) {
        super(nombre, numeroDocumento, telefono);
    }

    public ClienteDTO(int clienteID, String nombre, String numeroDocumento, String telefono) {
        super(nombre, numeroDocumento, telefono);
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
