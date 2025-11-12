package modelo.logica;

import modelo.dto.ClienteDTO;
import modelo.dto.HabitacionDTO;
import modelo.dto.ReservaDTO;
import modelo.dto.BoletaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;      
import java.sql.SQLException;   

import modelo.entidades.ServicioAdicional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GestorHotel {

    private ArrayList<ClienteDTO> listaClientes;
    private ArrayList<HabitacionDTO> listaHabitaciones;
    private ArrayList<ReservaDTO> listaReservas;
    private Connection con;

    public GestorHotel() {
        this.con = ConexionBD.conectar();
        
        this.listaClientes = new ArrayList<>();
        this.listaHabitaciones = new ArrayList<>();
        this.listaReservas = new ArrayList<>();

        if (this.con != null) {
            System.out.println("Gestor de Hotel inicializado. Conectado a BD.");
            System.out.println(" ");
            cargarClientesDesdeDB(); 
            
            java.util.Map<String, Integer> tiposHabitacionDB = cargarTiposHabitacionDesdeDB();
            if (tiposHabitacionDB.isEmpty()) {
                System.out.println("No se encontraron tipos de habitación en la BD. Insertando tipos por defecto...");
                 System.out.println(" ");
                insertarTipoHabitacion("Simple", 120.0);
                insertarTipoHabitacion("Doble", 200.0);
                insertarTipoHabitacion("Suite", 350.0);
                tiposHabitacionDB = cargarTiposHabitacionDesdeDB();
            }
            cargarHabitacionesIniciales(tiposHabitacionDB); 
        } else {
            System.out.println("¡¡ERROR!! Gestor en MODO DEMO. No se pudo conectar a la BD.");
            cargarHabitacionesIniciales(new java.util.HashMap<>());
        }
    }

    
//     Carga los tipos de habitación desde la tabla 'TipoHabitacion' de la BD.
//      Retorna un mapa con el nombre del tipo y su ID.
  
    private java.util.Map<String, Integer> cargarTiposHabitacionDesdeDB() {
        java.util.Map<String, Integer> tiposCargados = new java.util.HashMap<>();
        String sql = "SELECT id_tipo_habitacion, nombreTipo, precio FROM TipoHabitacion";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_tipo_habitacion");
                String nombre = rs.getString("nombreTipo");
                double precio = rs.getDouble("precio");
                tiposCargados.put(nombre, id);
            }
            System.out.println("Se cargaron " + tiposCargados.size() + " tipos de habitación desde la BD.");
             System.out.println(" ");
        } catch (SQLException e) {
            System.out.println("Error al cargar tipos de habitación: " + e.getMessage());
             System.out.println(" ");
        }
        return tiposCargados;
    }

   
//      Inserta un nuevo tipo de habitación en la tabla 'TipoHabitacion' de la BD.
//      Retorna el ID generado para el nuevo tipo de habitación, o -1 si falla.
     
    private int insertarTipoHabitacion(String nombreTipo, double precio) {
        String sql = "INSERT INTO TipoHabitacion (nombreTipo, precio) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, nombreTipo);
            pst.setDouble(2, precio);
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println("Tipo de habitación '" + nombreTipo + "' guardado en la BD.");
                         System.out.println(" ");
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar tipo de habitación '" + nombreTipo + "': " + e.getMessage());
             System.out.println(" ");
        }
        return -1;
    }

    
//     Carga la lista de clientes desde la tabla 'clientes' de la BD
//      al iniciar el gestor.
     
    private void cargarClientesDesdeDB() {
        String sql = "SELECT clienteID, nombres, numeroDocumento, telefono FROM clientes";
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                int clienteID = rs.getInt("clienteID");
                String nombre = rs.getString("nombres");
                String dni = rs.getString("numeroDocumento");
                String telefono = rs.getString("telefono");
                
                // Creamos el objeto ClienteDTO con el ID y lo añadimos a la lista local
                ClienteDTO c = new ClienteDTO(clienteID, nombre, dni, telefono);
                this.listaClientes.add(c);
            }
            System.out.println("Se cargaron " + this.listaClientes.size() + " clientes desde la BD.");
             System.out.println(" ");
            
        } catch (SQLException e) {
            System.out.println("Error fatal al cargar clientes: " + e.getMessage());
             System.out.println(" ");
        }
    }

//     Registra un nuevo cliente en la BD (tabla 'clientes') Y
//      también lo añade a la lista local en memoria.
     
    
    public ClienteDTO registrarNuevoCliente(String nombreForm, String dniForm, String telefonoForm) {
        
        String sql = "INSERT INTO clientes (nombres, tipoDocumento, numeroDocumento, telefono) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, nombreForm);
            pst.setString(2, "DNI"); // Asumimos "DNI" ya que el form no lo pide
            pst.setString(3, dniForm);
            pst.setString(4, telefonoForm);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente guardado en la BD: " + nombreForm);
                 System.out.println(" ");
                
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int clienteID = rs.getInt(1);
                        ClienteDTO nuevoCliente = new ClienteDTO(clienteID, nombreForm, dniForm, telefonoForm);
                        this.listaClientes.add(nuevoCliente);
                        return nuevoCliente;
                    }
                }
                System.out.println("Error: No se pudo recuperar el ID del cliente guardado.");
                 System.out.println(" ");
                return null;
                
            } else {
                System.out.println("Error: No se pudo guardar el cliente en la BD.");
                 System.out.println(" ");
                return null;
            }
            
        } catch (SQLException e) {
            System.out.println("Error de SQL al registrar cliente: " + e.getMessage());
             System.out.println(" ");
            // Esto es MUY probable si intentas registrar un DNI duplicado
            return null; 
        }
    }
    
    
   

    public ClienteDTO registrarNuevoCliente(String nombre, String dni) {
        return this.registrarNuevoCliente(nombre, dni, "No especificado");
    }

    private void cargarHabitacionesIniciales(java.util.Map<String, Integer> tiposHabitacionDB) {
        listaHabitaciones.add(new HabitacionDTO(101, "Simple", 120.0, false, tiposHabitacionDB.getOrDefault("Simple", -1)));
        listaHabitaciones.add(new HabitacionDTO(102, "Simple", 120.0, false, tiposHabitacionDB.getOrDefault("Simple", -1)));
        listaHabitaciones.add(new HabitacionDTO(201, "Doble", 200.0, false, tiposHabitacionDB.getOrDefault("Doble", -1)));
        listaHabitaciones.add(new HabitacionDTO(202, "Doble", 200.0, false, tiposHabitacionDB.getOrDefault("Doble", -1)));
        listaHabitaciones.add(new HabitacionDTO(301, "Suite", 350.0, false, tiposHabitacionDB.getOrDefault("Suite", -1)));
        listaHabitaciones.add(new HabitacionDTO(302, "Suite", 350.0, false, tiposHabitacionDB.getOrDefault("Suite", -1)));
        System.out.println("Se cargaron " + listaHabitaciones.size() + " habitaciones (de prueba).");
    }

    public ReservaDTO crearReserva(ClienteDTO cliente, HabitacionDTO habitacion, int dias, java.util.List<ServicioAdicional> servicios) {
        // ¡CORRECCIÓN! Solo se puede reservar si NO está ocupada.
        if (habitacion.isOcupada()) {
            System.out.println("ERROR: Se intentó reservar una habitación que ya está OCUPADA.");
             System.out.println(" ");
            return null;
        }
        
        ReservaDTO nuevaReserva = new ReservaDTO(cliente, habitacion, dias, servicios);
        double totalReserva = nuevaReserva.calcularImporteTotal();

        String sql = "INSERT INTO Reservas (clienteID, total) VALUES (?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, cliente.getClienteID());
            pst.setDouble(2, totalReserva);
            
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int reservaID = rs.getInt(1);
                        nuevaReserva.setReservaID(reservaID); // Asignar el ID generado a la reserva
                        this.listaReservas.add(nuevaReserva); // Añadir a la lista local
                        
                        // ¡CORRECCIÓN! Al reservar, la habitación pasa a estar OCUPADA (true).
                        habitacion.setOcupada(true);
                        System.out.println("Reserva creada y guardada en BD para " + cliente.getNombre() + " en la habitación " + habitacion.getNumero() + " con ID: " + reservaID);
                         System.out.println(" ");
                        return nuevaReserva;
                    }
                }
            }
            System.out.println("Error: No se pudo guardar la reserva en la BD.");
             System.out.println(" ");
            return null;
            
        } catch (SQLException e) {
            System.out.println("Error de SQL al crear reserva: " + e.getMessage());
             System.out.println(" ");
            return null;
        }
    }

    public BoletaDTO generarFactura(ReservaDTO reserva) {
        if (reserva == null || reserva.getReservaID() == 0) {
            System.out.println("ERROR: No se puede generar factura para una reserva nula o sin ID.");
             System.out.println(" ");
            return null;
        }

        BoletaDTO nuevaFactura = new BoletaDTO(reserva.getCliente(), reserva);
        
        String sql = "INSERT INTO Facturas (reservaID, fechaEmision, subTotal, igv, total, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, reserva.getReservaID());
            pst.setDate(2, java.sql.Date.valueOf(nuevaFactura.getFechaEmision()));
            pst.setDouble(3, nuevaFactura.getSubTotal());
            pst.setDouble(4, nuevaFactura.getIgv());
            pst.setDouble(5, nuevaFactura.getTotal());
            pst.setString(6, nuevaFactura.getEstado());

            System.out.println("DEBUG: Ejecutando INSERT en Facturas para Reserva ID: " + reserva.getReservaID());
             System.out.println(" ");
            int filasAfectadas = pst.executeUpdate();
            System.out.println("DEBUG: Filas afectadas por INSERT en Facturas: " + filasAfectadas);
             System.out.println(" ");

            if (filasAfectadas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int facturaID = rs.getInt(1);
                        nuevaFactura.setFacturaID(facturaID);
                        System.out.println("Factura generada y guardada en BD para Reserva ID " + reserva.getReservaID() + " con ID: " + facturaID);
                        return nuevaFactura;
                    }
                }
                System.out.println("DEBUG: No se pudo recuperar el ID generado para la factura.");
            }
            System.out.println("Error: No se pudo guardar la factura en la BD.");
            return null;

        } catch (SQLException e) {
            System.out.println("Error de SQL al generar factura: " + e.getMessage());
            return null;
        }
    }

    public HabitacionDTO buscarHabitacionPorTipo(String tipo) {
        for (HabitacionDTO habitacion : this.listaHabitaciones) {
            // ¡CORRECCIÓN! Buscamos las que NO estén ocupadas.
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && !habitacion.isOcupada()) {
                return habitacion;
            }
        }
        return null;
    }

    public String[] getTiposDeHabitacionDisponibles() {
        Set<String> tiposDisponibles = new HashSet<>();
        for (HabitacionDTO habitacion : this.listaHabitaciones) {
            // ¡CORRECCIÓN! Añadimos el tipo si la habitación NO está ocupada.
            if (!habitacion.isOcupada()) {
                tiposDisponibles.add(habitacion.getTipo());
            }
        }
        return tiposDisponibles.toArray(new String[0]);
    }

    public ArrayList<HabitacionDTO> getListaHabitaciones() {
        return this.listaHabitaciones;
    }

    public ArrayList<ReservaDTO> getListaReservas() {
        return this.listaReservas;
    }

    public ArrayList<ClienteDTO> getListaClientes() {
        return this.listaClientes;
    }

    public HabitacionDTO getHabitacionPorNumero(int numero) {
        for (HabitacionDTO habitacion : this.listaHabitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }
}