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
    public List<List<DataGraficDTO>> getListaDataGraficDias() throws SQLException {
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

    @Override
    public List<List<DataGraficDTO>> getListaDataGraficEdad() throws SQLException {
        List<List<DataGraficDTO>> listasDataGraficDTOs = new ArrayList<>();
        List<DataGraficDTO> listDayH25 = new ArrayList<>();
        List<DataGraficDTO> listDayH35 = new ArrayList<>();
        List<DataGraficDTO> listDayH45 = new ArrayList<>();
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
                if (objetito.getEdad()< 25) {
                    listDayH25.add(objetito);
                } else if (objetito.getEdad() < 35) {
                    listDayH35.add(objetito);
                } else if (objetito.getDias() < 45) {
                    listDayH45.add(objetito);
                } else {
                    listDayHmore.add(objetito);
                }

                listasDataGraficDTOs.add(listDayH25);
                listasDataGraficDTOs.add(listDayH35);
                listasDataGraficDTOs.add(listDayH45);
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

    @Override
    public List<List<DataGraficDTO>> getListaDataGraficSatisfacccion() throws SQLException {
        List<List<DataGraficDTO>> listasDataGraficDTOs = new ArrayList<>();
        List<DataGraficDTO> listDayH12 = new ArrayList<>();
        List<DataGraficDTO> listDayH3 = new ArrayList<>();
        List<DataGraficDTO> listDayH4 = new ArrayList<>();
        List<DataGraficDTO> listDayH5 = new ArrayList<>();
        String sql = "SELECT nombre, edad, servicioPick, dias, satisfaccion FROM datosparagrafico;";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DataGraficDTO objetito = new DataGraficDTO();
                objetito.setName(rs.getString("nombre"));
                objetito.setDias(rs.getInt("dias"));
                objetito.setEdad(rs.getInt("edad"));
                objetito.setSatisfaccion(rs.getInt("satisfaccion"));
                objetito.setServicioPick(rs.getInt("servicioPick"));
                if (objetito.getSatisfaccion()<3) {
                    listDayH12.add(objetito);
                } else if (objetito.getSatisfaccion()< 4) {
                    listDayH3.add(objetito);
                } else if (objetito.getSatisfaccion()< 5) {
                    listDayH4.add(objetito);
                } else {
                    listDayH5.add(objetito);
                }

                listasDataGraficDTOs.add(listDayH12);
                listasDataGraficDTOs.add(listDayH3);
                listasDataGraficDTOs.add(listDayH4);
                listasDataGraficDTOs.add(listDayH5);

                if (listasDataGraficDTOs.isEmpty()) {
                } else {
                    System.out.println("objeto guardado");
                }
            }
            conn.commit();
            return listasDataGraficDTOs;
        }
    }

    @Override
    public List<List<DataGraficDTO>> getListaDataGraficServiciosExtra() throws SQLException {
          List<List<DataGraficDTO>> listasDataGraficDTOs = new ArrayList<>();
        List<DataGraficDTO> listDaySauna = new ArrayList<>();
        List<DataGraficDTO> listDayAgua = new ArrayList<>();
        List<DataGraficDTO> listDayEstacionamiento = new ArrayList<>();
        String sql = "SELECT nombre, edad, servicioPick, dias, satisfaccion FROM datosparagrafico;";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DataGraficDTO objetito = new DataGraficDTO();
                objetito.setName(rs.getString("nombre"));
                objetito.setDias(rs.getInt("dias"));
                objetito.setEdad(rs.getInt("edad"));
                objetito.setSatisfaccion(rs.getInt("satisfaccion"));
                objetito.setServicioPick(rs.getInt("servicioPick"));
                if (objetito.getServicioPick()==1) {
                    listDaySauna.add(objetito);
                } else if (objetito.getServicioPick()==2) {
                    listDayAgua.add(objetito);
                } else if (objetito.getServicioPick()==3) {
                    listDayEstacionamiento.add(objetito);
                } 

                listasDataGraficDTOs.add(listDaySauna);
                listasDataGraficDTOs.add(listDayAgua);
                listasDataGraficDTOs.add(listDayEstacionamiento);
         

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
