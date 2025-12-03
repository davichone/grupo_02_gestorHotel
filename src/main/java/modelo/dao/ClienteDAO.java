package modelo.dao;

import modelo.dto.ClienteDTO;
import modelo.logica.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar operaciones CRUD en la tabla Clientes.
 */
public class ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente El cliente a insertar.
     * @return El ID generado para el cliente insertado.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public int insertar(ClienteDTO cliente) throws SQLException {
        
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new SQLException("El nombre del cliente no puede estar vacío.");
        }
        if (cliente.getDni() == null || cliente.getDni().trim().isEmpty()) {
            throw new SQLException("El documento (DNI) es obligatorio.");
        }
        
        String sql = "INSERT INTO clientes (nombres, numeroDocumento, tipoDocumento, telefono, email) "
               + "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        // IMPORTANTE: que estos getters existan y NO devuelvan null
        ps.setString(1, cliente.getNombre());        // nombres
        ps.setString(2, cliente.getDni());           // numeroDocumento
        ps.setString(3, cliente.getTipoDocumento() != null ? cliente.getTipoDocumento() : "DNI"); 
        ps.setString(4, cliente.getTelefono());
        ps.setString(4, cliente.getTelefono());      // telefono
        ps.setString(5, cliente.getEmail());         // email, también puede ser null

        int filas = ps.executeUpdate();

        if (filas > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // clienteID generado
                }
            }
        }
        return -1;
    }
    }

    /**
     * Actualiza un cliente existente.
     * @param cliente El cliente actualizado.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void actualizar(ClienteDTO cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombres = ?, tipoDocumento = ?, numeroDocumento = ?, telefono = ? WHERE clienteID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, "DNI");
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getClienteID());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina un cliente por ID.
     * @param id El ID del cliente a eliminar.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE clienteID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Busca un cliente por ID.
     * @param id El ID del cliente.
     * @return El cliente encontrado o null si no existe.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public ClienteDTO buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE clienteID = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClienteDTO(
                        rs.getInt("clienteID"),
                        rs.getString("nombres"),
                        rs.getString("numeroDocumento"),
                        rs.getString("telefono")
                    );
                }
            }
            return null;
        }
    }

    /**
     * Lista todos los clientes.
     * @return Lista de clientes.
     * @throws SQLException Si ocurre un error en la BD.
     */
    public List<ClienteDTO> listarTodos() throws SQLException {
        List<ClienteDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ClienteDTO(
                    rs.getInt("clienteID"),
                    rs.getString("nombres"),
                    rs.getString("numeroDocumento"),
                    rs.getString("telefono")
                ));
            }
        }
        return lista;
    }

    public int insertar(ClienteDTO cliente, Connection conn) throws SQLException {
     String sql = "INSERT INTO clientes (nombres, numeroDocumento, tipoDocumento, telefono, email) VALUES (?, ?, ?, ?, ?)";

     // IMPORTANTE: NO uses try-with-resources en 'conn' aquí, porque la conexión viene de fuera
     try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

         ps.setString(1, cliente.getNombre());
         ps.setString(2, cliente.getDni());
         ps.setString(3, cliente.getTipoDocumento() != null ? cliente.getTipoDocumento() : "DNI");
         ps.setString(4, cliente.getTelefono());
         ps.setString(5, cliente.getEmail());

         int filas = ps.executeUpdate();
         if (filas > 0) {
             try (ResultSet rs = ps.getGeneratedKeys()) {
                 if (rs.next()) {
                     return rs.getInt(1); // Retorna el ID nuevo
                 }
             }
         }
         // Si llegamos aquí, falló
         throw new SQLException("No se pudo registrar el cliente (ID no generado)."); 
     }                                                                                                                                                                                                                  
}

    ClienteDTO buscarPorId(int aInt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}