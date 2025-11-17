/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.servicio;

import modelo.dao.BoletaDAO;
import modelo.dto.BoletaDTO;
import modelo.logica.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BoletaService {
    private BoletaDAO boletaDAO = new BoletaDAO();

    public int generarFactura(BoletaDTO boleta) throws SQLException {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            int id = boletaDAO.insertar(boleta, conn);
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
