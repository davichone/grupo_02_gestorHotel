/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.sql.SQLException;
import java.util.List;
import modelo.dto.ItemDTO;

/**
 *
 * @author drola
 */
public interface ItemDAO {

void addItem (ItemDTO item) throws SQLException;
void deleatItem(ItemDTO item) throws SQLException;
List<ItemDTO>getStockParaTabla() throws SQLException;

    
}
