package modelo.dto;

public class HabitacionDTO {
    private int numero;
    private String tipo;
    private double precio; 
    private boolean estaOcupada;
    private int idTipoHabitacion;

    public HabitacionDTO(int numero, String tipo, double precio, boolean ocupadaInicial){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio; 
        this.estaOcupada = ocupadaInicial;
    }
    
    public HabitacionDTO(int numero, String tipo, double precio, boolean ocupadaInicial, int idTipoHabitacion){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio; 
        this.estaOcupada = ocupadaInicial;
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public int getNumero() { 
        return numero; 
    }
    public String getTipo() { 
        return tipo; 
    }
    public double getPrecio() { 
        return precio; 
    }
    public boolean isOcupada() { 
        return estaOcupada; 
    }
    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setOcupada(boolean estado) {
        this.estaOcupada = estado;
    }
    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getEstadoTexto() {
        return estaOcupada ? "OCUPADA" : "DISPONIBLE";
    }
}
