/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.SQLException;
import java.util.List;
import modelo.dto.DataGraficDTO;

/**
 *
 * @author drola
 */
public interface DataGraficDAO {

    List<List<DataGraficDTO>> getListaDataGraficDias() throws SQLException;

    List<List<DataGraficDTO>> getListaDataGraficEdad() throws SQLException;

    List<List<DataGraficDTO>> getListaDataGraficSatisfacccion() throws SQLException;

    List<List<DataGraficDTO>> getListaDataGraficServiciosExtra() throws SQLException;

}
