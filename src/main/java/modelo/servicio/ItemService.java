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
import modelo.dao.ItemDAO;
import modelo.dto.ItemDTO;
import modelo.logica.ConexionBD;

/**
 *
 * @author drola
 */
public class ItemService implements ItemDAO{

    @Override
    public void addItem(ItemDTO item) throws SQLException {
        
    }

    @Override
    public void deleatItem(ItemDTO item) throws SQLException {
        
    }

    @Override
    public List<ItemDTO> getStockParaTabla() throws SQLException {
        
        String sql = " SELECT org, cod, familia, sub_familia, item, existencia, fecha_entrada, estado FROM stock ";
        List<ItemDTO> listaItems =new ArrayList<>();
        try(Connection conn = ConexionBD.conectar();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                ItemDTO item = new ItemDTO();
                item.setOrg(rs.getString("org"));
                item.setCod(rs.getString("cod"));
                item.setFamilia(rs.getString("familia"));
                item.setSubFamilia(rs.getString("sub_familia"));
                item.setItem(rs.getString("item"));
                item.setExistencia(rs.getString("existencia"));
                item.setFechaEntrada(rs.getString("fecha_entrada"));
                item.setEstado(rs.getString("estado"));
                listaItems.add(item);
            }
            
            
        }
        
        return listaItems;
        
    }
    
}
