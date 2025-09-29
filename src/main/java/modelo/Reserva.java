package modelo;

public class Reserva {
    // PRECIOS FIJOS PARA LOS EXTRAS (CONSTANTES)
    private static final double PRECIO_SAUNA = 30.0;
    private static final double PRECIO_AGUA_CALIENTE = 10.0;
    private static final double PRECIO_ESTACIONAMIENTO = 20.0;

    private Cliente cliente;
    private Habitacion habitacion;
    private int dias;
    
    // ATRIBUTOS AÑADIDOS PARA LOS EXTRAS
    private boolean conSauna;
    private boolean conAguaCaliente;
    private boolean conEstacionamiento;

    // CONSTRUCTOR ACTUALIZADO para recibir los extras
    public Reserva(Cliente cliente, Habitacion habitacion, int dias, boolean conSauna, boolean conAguaCaliente, boolean conEstacionamiento) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.dias = dias;
        this.conSauna = conSauna;
        this.conAguaCaliente = conAguaCaliente;
        this.conEstacionamiento = conEstacionamiento;
    }

    // MÉTODO DE CÁLCULO ACTUALIZADO
    public double calcularImporteTotal() {
        if (habitacion == null) {
            return 0;
        }

        // 1. Empezamos con el costo base de la habitación
        double total = habitacion.getPrecio() * dias;

        // 2. Sumamos el costo de cada extra si fue seleccionado
        if (conSauna) {
            total += PRECIO_SAUNA;
        }
        if (conAguaCaliente) {
            total += PRECIO_AGUA_CALIENTE;
        }
        if (conEstacionamiento) {
            total += PRECIO_ESTACIONAMIENTO;
        }

        return total;
    }
    
    // --- Getters y Setters (sin cambios, pero puedes añadirlos para los extras si los necesitas) ---  
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Habitacion getHabitacion() { return habitacion; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
    public int getDias() { return dias; }
    public void setDias(int dias) { this.dias = dias; }

    public String getSauna() {
        if(this.conSauna){
          String s = "Sauna";
            return  s;
         }else{
            String s = "-";
            return s;
        }
    }

     public String getAguaCaliente() {
        if(this.conAguaCaliente){
          String s = "Agua caliente";
            return  s;
        }else{
            String s = "-";
            return s;
        }
        
    }
     
      public String getEstacionamiento() {
        if(this.conEstacionamiento){
          String s = "Estacionamiento";
            return  s;
        }else{
            String s = "-";
            return s;
        }
    }
}