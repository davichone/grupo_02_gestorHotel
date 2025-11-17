package modelo.dto;

public class EmpleadoDTO {
    private int empleadoID;
    private int personaID;
    private String codigoEmpleado; // opcional, si usas código interno
    private boolean activo = true;

    // Constructor vacío
    public EmpleadoDTO() {}

    // Getters y Setters
    public int getEmpleadoID() { return empleadoID; }
    public void setEmpleadoID(int empleadoID) { this.empleadoID = empleadoID; }

    public int getPersonaID() { return personaID; }
    public void setPersonaID(int personaID) { this.personaID = personaID; }

    public String getCodigoEmpleado() { return codigoEmpleado; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "empleadoID=" + empleadoID +
                ", personaID=" + personaID +
                ", codigoEmpleado='" + codigoEmpleado + '\'' +
                ", activo=" + activo +
                '}';
    }
}