package modelo.servicio;

import modelo.dao.ClienteDAO;
import modelo.dto.ClienteDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public int registrarCliente(ClienteDTO cliente) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            int id = clienteDAO.insertar(cliente, conn);
            ConexionBD.commit(conn);
            return id;
        } catch (SQLException e) {
            ConexionBD.rollback(conn);
            throw e;
        } finally {
            ConexionBD.cerrar(conn);
        }
    }
}
