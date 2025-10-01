package modelo;

public class Habitacion {
    private int numero;
    private String tipo;
    private double precio; // Atributo añadido
    private boolean estaOcupada;

    public Habitacion(int numero, String tipo, double precio, boolean ocupadaInicial){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio; // Asignación añadida
        this.estaOcupada = ocupadaInicial;
    }

    // --- Getters y Setters ---
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getNumero(){
        return numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){ // Método corregido
        this.tipo = tipo;
    }
   public void cambiarEstado() {
        this.estaOcupada = !this.estaOcupada; 
    }
   
 public boolean estaOcupada() {
        return estaOcupada;
    }

    public String getEstadoTexto() {
        return estaOcupada ? "OCUPADA" : "DISPONIBLE";
    }

    public void estaOcupada(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
