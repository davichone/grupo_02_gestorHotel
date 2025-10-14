package logica;

import interfaces.ServicioAdicional;
import modelo.*; // Importa todas las clases del modelo de una vez
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GestorHotel {
    // Atributos: Las listas que actúan como nuestra "base de datos" en memoria
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Habitacion> listaHabitaciones;
    private ArrayList<Reserva> listaReservas;
      
    public GestorHotel() {
        
        this.listaClientes = new ArrayList<>();
        this.listaHabitaciones = new ArrayList<>();
        this.listaReservas = new ArrayList<>();

        System.out.println("Gestor de Hotel inicializado. Cargando habitaciones de prueba");
        cargarHabitacionesIniciales();
    }
    
   private void cargarHabitacionesIniciales() {
        // El estado 'ocupada' en false significa DISPONIBLE
        listaHabitaciones.add(new Habitacion(101, "Simple", 120.0, false)); // false = Disponible
        listaHabitaciones.add(new Habitacion(102, "Simple", 120.0, false));  // true = Ocupada
        listaHabitaciones.add(new Habitacion(103, "Simple", 120.0, false));
        listaHabitaciones.add(new Habitacion(201, "Doble", 200.0, false));
        listaHabitaciones.add(new Habitacion(202, "Doble", 200.0, false));
        listaHabitaciones.add(new Habitacion(203, "Doble", 200.0, false));
        listaHabitaciones.add(new Habitacion(301, "Suite", 350.0, false));
        listaHabitaciones.add(new Habitacion(302, "Suite", 350.0, false));
        System.out.println("Se cargaron " + listaHabitaciones.size() + " habitaciones.");
    }
   
    public Cliente registrarNuevoCliente(String nombre, String dni, String telefono) {
        // Validar si el cliente ya existe podría ser una buena mejora aquí
        Cliente nuevoCliente = new Cliente(nombre, dni, telefono);
        this.listaClientes.add(nuevoCliente);
        System.out.println("Cliente registrado en el gestor: " + nuevoCliente.getNombre());
        return nuevoCliente;
    }

    public Reserva crearNuevaReserva(Cliente cliente, Habitacion habitacion, int dias, java.util.List<ServicioAdicional> servicios) {
        // ¡CORRECCIÓN! Solo se puede reservar si NO está ocupada.
        if (habitacion.isOcupada()) {
            System.out.println("ERROR: Se intentó reservar una habitación que ya está OCUPADA.");
            return null; // Retornamos null para indicar que la operación falló
        }

        Reserva nuevaReserva = new Reserva(cliente, habitacion, dias, servicios);
        this.listaReservas.add(nuevaReserva);
        
        // ¡CORRECCIÓN! Al reservar, la habitación pasa a estar OCUPADA (true).
        habitacion.setOcupada(true); 

        System.out.println("Reserva creada para " + cliente.getNombre() + " en la habitación " + habitacion.getNumero());
        return nuevaReserva;
    }
    
    public Habitacion buscarHabitacionPorTipo(String tipo) {
        for (Habitacion habitacion : this.listaHabitaciones) {
            // ¡CORRECCIÓN! Buscamos las que NO estén ocupadas.
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && !habitacion.isOcupada()) {
                return habitacion; // Devuelve la primera disponible que encuentra
            }
        }
        return null; // No encontró ninguna disponible de ese tipo
    }
    
    public String[] getTiposDeHabitacionDisponibles() {
        Set<String> tiposDisponibles = new HashSet<>();
        for (Habitacion habitacion : this.listaHabitaciones) {
            // ¡CORRECCIÓN! Añadimos el tipo si la habitación NO está ocupada.
            if (!habitacion.isOcupada()) {
                tiposDisponibles.add(habitacion.getTipo());
            }
        }
        return tiposDisponibles.toArray(new String[0]);
    }
    
    public ArrayList<Habitacion> getListaHabitaciones() {
        return this.listaHabitaciones;
    }
    
    public ArrayList<Reserva> getListaReservas() {
        return this.listaReservas;
    }

    // ¡CORRECCIÓN! Este método devolvía la lista de reservas.
    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public Habitacion getHabitacionPorNumero(int numero) {
        for (Habitacion habitacion : this.listaHabitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null; // No la encontró
    }
}
