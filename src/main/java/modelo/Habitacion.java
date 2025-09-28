package modelo;

public class Habitacion {
    private int numero;
    private String tipo;
    private double precio; // Atributo añadido
    private boolean disponible;

    public Habitacion(int numero, String tipo, double precio, boolean disponible){
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio; // Asignación añadida
        this.disponible = disponible;
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
    public boolean isDisponible(){
        return disponible;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
}