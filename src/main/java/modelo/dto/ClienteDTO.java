package modelo.dto;

import modelo.entidades.Persona;

/**
 * @author extru
 */
public class ClienteDTO extends Persona {
    
    private int clienteID;
    private String tipoDocumento;

    public ClienteDTO(int aInt, String nombre, String numeroDocumento, String telefono) {
        this.nombre = nombre;
        this.dni = numeroDocumento;
        this.telefono = telefono;
    }

    public ClienteDTO(int clienteID, String nombre, String dni, String telefono, String tipoDocumento, String email) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
    }

    public int getClienteID() { 
        return clienteID; 
    }
    public void setClienteID(int clienteID) { 
        this.clienteID = clienteID; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getDni() { 
        return dni; }
    public void setDni(String dni) { 
        this.dni = dni; }

    public String getTelefono() { 
        return telefono; }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; }

    public String getTipoDocumento() { 
        return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { 
        this.tipoDocumento = tipoDocumento; }

    public String getEmail() { 
        return email; }
    public void setEmail(String email) { 
        this.email = email; }

    @Override
    public String getRol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

