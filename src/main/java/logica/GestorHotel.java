package logica;

import modelo.*; // Importa todas las clases del modelo de una vez
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;

public class GestorHotel {
    // Atributos: Las listas que actúan como nuestra "base de datos" en memoria
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Habitacion> listaHabitaciones;
    private ArrayList<Reserva> listaReservas;

    // Constructor: Se ejecuta al crear un objeto GestorHotel
    
    
    //pruebita:
    
    public Habitacion getHabitacionPorNumero(int numero) {
    for (Habitacion habitacion : this.listaHabitaciones) {
        if (habitacion.getNumero() == numero) {
            return habitacion;
        }
    }
    return null; // No la encontró
}
    
    public boolean cambiarEstadoHabitacion(int numero) {
    Habitacion h = getHabitacionPorNumero(numero);
    if (h != null) {
        // Ejecuta la lógica POO en el objeto H
        h.cambiarEstado(); 
        System.out.println("Estado de Habitación " + numero + " cambiado a " + h.getEstadoTexto());
        return true;
    }
    return false;
}
    
    
    public GestorHotel() {
        
        this.listaClientes = new ArrayList<>();
        this.listaHabitaciones = new ArrayList<>();
        this.listaReservas = new ArrayList<>();

        System.out.println("Gestor de Hotel inicializado. Cargando habitaciones de prueba...");
        cargarHabitacionesIniciales();
    }

    public java.util.ArrayList<modelo.Reserva> getListaReservas() {
        return this.listaReservas;
    }

    public ArrayList<Reserva> getListaClientes() {
        return this.listaReservas;
    }
    

    // Método privado para mantener el constructor limpio
   private void cargarHabitacionesIniciales() {
        // Para este ejemplo, solo cargaremos las habitaciones que usaremos en la interfaz
        listaHabitaciones.add(new Habitacion(101, "Simple", 120.0, false)); // false = Disponible
        listaHabitaciones.add(new Habitacion(102, "Doble", 200.0, true));  // true = Ocupada
        listaHabitaciones.add(new Habitacion(103, "Simple", 120.0, false));
        listaHabitaciones.add(new Habitacion(104, "Suite", 350.0, true));
        System.out.println("Se cargaron " + listaHabitaciones.size() + " habitaciones.");
    }
    /**
    * Recibe datos de un cliente desde la vista, crea un objeto Cliente,
    * lo guarda en la lista y lo devuelve.
    * @param nombre El nombre del cliente.
    * @param dni El DNI del cliente.
    * @return El objeto Cliente recién creado.
    */
    public Cliente registrarNuevoCliente(String nombre, String dni,String telefono) {

        Cliente nuevoCliente = new Cliente(nombre, dni,telefono);
        this.listaClientes.add(nuevoCliente);
        System.out.println("Cliente registrado en el gestor: " + nuevoCliente.getNombre());

        return nuevoCliente;
    }

    /**
     * Recibe un cliente, una habitación y los días, crea un objeto Reserva,
     * actualiza la disponibilidad de la habitación y guarda la reserva.
     * @param cliente El objeto Cliente que hace la reserva.
     * @param habitacion El objeto Habitacion a reservar.
     * @param dias El número de días de la estancia.
     * @return El objeto Reserva recién creado.
     */
    public Reserva crearNuevaReserva(Cliente cliente, Habitacion habitacion, int dias, boolean conSauna, boolean conAguaCaliente, boolean conEstacionamiento) {
        if (!habitacion.estaOcupada()) {
            System.out.println("ERROR: Se intentó reservar una habitación no disponible.");
            return null;
        }

        Reserva nuevaReserva = new Reserva(cliente, habitacion, dias, conSauna, conAguaCaliente, conEstacionamiento);
        this.listaReservas.add(nuevaReserva);
        habitacion.estaOcupada(false);
    
        System.out.println("Reserva creada para " + cliente.getNombre() + " con extras.");
        return nuevaReserva;  
    }
    
    
    /**
     * Busca la primera habitación disponible que coincida con un tipo específico.
     * @param tipo El tipo de habitación a buscar (ej. "single room").
     * @return Un objeto Habitacion si encuentra una disponible, o null si no hay ninguna.
     */
        public Habitacion buscarHabitacionPorTipo(String tipo) {
        for (Habitacion habitacion : this.listaHabitaciones) {
            // Comprueba si el tipo coincide (ignorando mayúsculas/minúsculas) Y si está disponible
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && habitacion.estaOcupada()) {
                return habitacion; // Devuelve la primera que encuentra
            }
        }
        return null; // Si el bucle termina, es que no encontró ninguna
    }
    /**
     * Devuelve un arreglo de strings con los tipos de habitación ÚNICOS que
     * tienen al menos una habitación disponible.
     * @return Un arreglo de Strings para usar en un JComboBox.
     */
    public String[] getTiposDeHabitacionDisponibles() {
        // Usamos un 'Set' para evitar tipos duplicados (ej: "Simple", "Simple", "Doble" -> "Simple", "Doble")
        Set<String> tiposDisponibles = new HashSet<>();
        
        for (Habitacion habitacion : this.listaHabitaciones) {
            if (habitacion.estaOcupada()) {
                tiposDisponibles.add(habitacion.getTipo());
            }
        }
        // Convertimos el Set a un arreglo de Strings y lo devolvemos
        return tiposDisponibles.toArray(new String[0]);
    }

    //public ArrayList<Reserva> getListaReservas() {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
