package modelo.servicio;

import modelo.dao.PersonaDAO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.UsuarioDAO;
import modelo.dto.PersonaDTO;
import modelo.dto.EmpleadoDTO;
import modelo.dto.UsuarioDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class EmpleadoService {

    private final PersonaDAO personaDAO = new PersonaDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Registra un empleado completo: Persona + Empleado + Usuario (con login)
     */
    public int registrarEmpleadoCompleto(PersonaDTO persona, EmpleadoDTO empleado, UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            conn.setAutoCommit(false); // Iniciar transacción

            // 1. Insertar Persona
            int personaId = personaDAO.insertar(persona, conn);
            if (personaId <= 0) throw new SQLException("No se pudo insertar la persona");

            // 2. Insertar Empleado (asignamos la persona)
            empleado.setPersonaID(personaId);
            int empleadoId = empleadoDAO.insertar(empleado, conn);
            if (empleadoId <= 0) throw new SQLException("No se pudo insertar el empleado");

            // 3. Insertar Usuario (login del sistema)
            usuario.setPersonaID(personaId);
            // Aseguramos que tenga usuario y contraseña
            if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty()) {
                usuario.setUsuario(persona.getNumeroDocumento()); // Usa DNI como usuario por defecto
            }
            if (usuario.getPassword_plano() == null || usuario.getPassword_plano().trim().isEmpty()) {
                throw new SQLException("La contraseña es obligatoria");
            }

            int usuarioId = usuarioDAO.insertar(usuario, conn);
            if (usuarioId <= 0) throw new SQLException("No se pudo crear el usuario del sistema");

            conn.commit();
            return empleadoId;

        } catch (SQLException e) {
            if (conn != null) ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }
}