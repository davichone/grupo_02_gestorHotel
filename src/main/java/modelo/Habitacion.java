package modelo;

public class Habitacion {
    private int numero;
    private String tipo;
    private double precio; 
    private boolean estaOcupada;

    public Habitacion(int numero, String tipo, double precio, boolean ocupadaInicial){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio; 
        this.estaOcupada = ocupadaInicial;
    }

    // --- Getters ---
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

    // --- Setters ---
    public void setOcupada(boolean estado) {
        this.estaOcupada = estado;
    }

    // --- Métodos de utilidad ---
    public String getEstadoTexto() {
        return estaOcupada ? "OCUPADA" : "DISPONIBLE";
    }
}
