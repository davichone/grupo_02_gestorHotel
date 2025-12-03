package modelo.dao;

import modelo.dto.HabitacionDTO;
import modelo.logica.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar operaciones CRUD en la tabla Habitaciones.
 * Adaptado a la BD donde la columna de precio se llama 'precioPorNoche'
 * y el estado se guarda como texto ('DISPONIBLE' / 'OCUPADA').
 */
public class HabitacionDAO {

    /**
     * Inserta una nueva habitación en la base de datos.
     * Mapea:
     *  - HabitacionDTO.precio      -> columna 'precioPorNoche'
     *  - HabitacionDTO.isOcupada() -> columna 'estado' ('OCUPADA' / 'DISPONIBLE')
     */
    public int insertar(HabitacionDTO habitacion) throws SQLException {
        String sql = "INSERT INTO Habitaciones (numero, tipo, precioPorNoche, estado) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getTipo());
            ps.setDouble(3, habitacion.getPrecio());
            ps.setString(4, habitacion.isOcupada() ? "OCUPADA" : "DISPONIBLE");

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // habitacionID generado
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Actualiza una habitación existente.
     */
    public void actualizar(HabitacionDTO habitacion) throws SQLException {
        String sql = "UPDATE Habitaciones "
                   + "SET tipo = ?, precioPorNoche = ?, estado = ? "
                   + "WHERE numero = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, habitacion.getTipo());
            ps.setDouble(2, habitacion.getPrecio());
            ps.setString(3, habitacion.isOcupada() ? "OCUPADA" : "DISPONIBLE");
            ps.setInt(4, habitacion.getNumero());

            ps.executeUpdate();
        }
    }

    /**
     * Elimina una habitación por número.
     */
    public void eliminar(int numero) throws SQLException {
        String sql = "DELETE FROM Habitaciones WHERE numero = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ps.executeUpdate();
        }
    }

    /**
     * Busca una habitación por número.
     */
    public HabitacionDTO buscarPorNumero(int numeroBusqueda) throws SQLException {
        String sql = "SELECT habitacionID, numero, tipo, precioPorNoche, estado FROM Habitaciones WHERE numero = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numeroBusqueda);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idInterno = rs.getInt("habitacionID"); // Recuperamos el ID (ej: 1)
                    int num = rs.getInt("numero");             // Recuperamos el numero (ej: 101)
                    String tipo = rs.getString("tipo");
                    double precio = rs.getDouble("precioPorNoche");
                    String estado = rs.getString("estado");
                    boolean ocupada = "OCUPADA".equalsIgnoreCase(estado);

                    // Creamos el objeto
                    HabitacionDTO habitacion = new HabitacionDTO(num, tipo, precio, ocupada);

                    // CORRECCIÓN B: ¡Guardamos el ID interno en el objeto!
                    habitacion.setHabitacionID(idInterno);

                    return habitacion;
                }
            }
            return null;
        }
    }

    /**
     * Lista todas las habitaciones.
     */
    public List<HabitacionDTO> listarTodas() throws SQLException {
        List<HabitacionDTO> lista = new ArrayList<>();

        // 1. IMPORTANTE: Agregamos 'habitacionID' a la consulta
        String sql = "SELECT habitacionID, numero, tipo, precioPorNoche, estado FROM Habitaciones";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idInterno = rs.getInt("habitacionID"); // Recuperamos el ID real (1, 2, 3...)
                int num = rs.getInt("numero");
                String tipo = rs.getString("tipo");
                double precio = rs.getDouble("precioPorNoche");
                String estado = rs.getString("estado");
                boolean ocupada = "OCUPADA".equalsIgnoreCase(estado);

                // Creamos el DTO
                HabitacionDTO hab = new HabitacionDTO(num, tipo, precio, ocupada);

                // 2. IMPORTANTE: Inyectamos el ID al objeto
                hab.setHabitacionID(idInterno);

                lista.add(hab);
            }
        }
        return lista;
    }

    /**
     * Marca una habitación como ocupada (estado = 'OCUPADA').
     */
    public void marcarOcupada(int numero, Connection conn) throws SQLException {
        String sql = "UPDATE Habitaciones "
                   + "SET estado = 'OCUPADA' "
                   + "WHERE numero = ? AND estado <> 'OCUPADA'";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Habitación no disponible: " + numero);
            }
        }
    }

    /**
     * Marca una habitación como disponible (estado = 'DISPONIBLE').
     */
    public void marcarDisponible(int numero, Connection conn) throws SQLException {
        String sql = "UPDATE Habitaciones SET estado = 'DISPONIBLE' WHERE numero = ?";
         try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numero);
            ps.executeUpdate();
        }
    }

    // Versión con Connection aún sin implementar
    HabitacionDTO buscarPorNumero(int aInt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

