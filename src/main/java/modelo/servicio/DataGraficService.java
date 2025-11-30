/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.DataGraficDAO;
import modelo.dto.DataGraficDTO;
import modelo.logica.ConexionBD;

/**
 *
 * @author drola
 */
public class DataGraficService implements DataGraficDAO {

    @Override
    public List<List<DataGraficDTO>> getListaDataGrafic() throws SQLException {
        List<List<DataGraficDTO>> listasDataGraficDTOs = new ArrayList<>();
        List<DataGraficDTO> listDayH5 = new ArrayList<>();
        List<DataGraficDTO> listDayH10 = new ArrayList<>();
        List<DataGraficDTO> listDayH15 = new ArrayList<>();
        List<DataGraficDTO> listDayHmore = new ArrayList<>();
        String sql = "SELECT nombre, edad, servicioPick, dias, satisfaccion FROM datosparagrafico;";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DataGraficDTO objetito = new DataGraficDTO();
                objetito.setName(rs.getString("nombre"));
                objetito.setDias(rs.getInt("dias"));
                objetito.setEdad(rs.getInt("edad"));
                objetito.setSatisfaccion(rs.getInt("satisfaccion"));
                objetito.setServicioPick(rs.getInt("servicioPick"));
                if (objetito.getDias() < 5) {
                    listDayH5.add(objetito);
                } else if (objetito.getDias() < 10) {
                    listDayH10.add(objetito);
                } else if (objetito.getDias() < 15) {
                    listDayH15.add(objetito);
                } else {
                    listDayHmore.add(objetito);
                }

                listasDataGraficDTOs.add(listDayH5);
                listasDataGraficDTOs.add(listDayH10);
                listasDataGraficDTOs.add(listDayH15);
                listasDataGraficDTOs.add(listDayHmore);

                if (listasDataGraficDTOs.isEmpty()) {
                } else {
                    System.out.println("objeto guardado");
                }
            }
            conn.commit();
            return listasDataGraficDTOs;
        }

    }

}
