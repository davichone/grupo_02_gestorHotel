package modelo.entidades;

import java.time.LocalDate;
/**
 *
 * @author alexg
 */
public class Empleado extends Persona {
    private int empleadosID;
    private LocalDate fechaContratacion;
    private double salario;
    private boolean activo;

    public int getEmpleadosID() {
        return empleadosID;
    }

    public void setEmpleadosID(int empleadosID) {
        this.empleadosID = empleadosID;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    // Para registro inicial
    public Empleado(String nombre, String numeroDocumento, String telefono, String email, String tipoDocumento) {
        super(nombre, numeroDocumento, telefono, email, tipoDocumento);
        // Valores por defecto
        this.salario = 0.00; 
        this.fechaContratacion = LocalDate.now();
        this.activo = true;
    }

    @Override
    public String getRol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getPersonaID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
