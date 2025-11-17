// modelo/servicio/EmpleadoService.java
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

    private final PersonaDAO personaDAO;
    private final EmpleadoDAO empleadoDAO;
    private final UsuarioDAO usuarioDAO;

    public EmpleadoService() {
        this.personaDAO = new PersonaDAO();
        this.empleadoDAO = new EmpleadoDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public int registrarEmpleadoCompleto(PersonaDTO persona, EmpleadoDTO empleado, UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();

            // 1. Insertar persona
            int personaId = personaDAO.insertar(persona, conn);
            if (personaId == -1) throw new SQLException("Error al registrar persona");

            // 2. Insertar empleado
            empleado.setPersonaID(personaId);
            int empleadoId = empleadoDAO.insertar(empleado, conn);
            if (empleadoId == -1) throw new SQLException("Error al registrar empleado");

            // 3. Insertar usuario
            usuario.setPersonaID(personaId);
            int usuarioId = usuarioDAO.insertar(usuario, conn);
            if (usuarioId == -1) throw new SQLException("Error al registrar usuario");

            ConexionBD.commit(conn);
            return empleadoId;

        } catch (SQLException e) {
            ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }
}