/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.servicio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author drola
 */
public class EcxelService {
    
    public static void exportar( JTable tabla, File archivoDestino) throws IOException{
        //obtengo el modelo
        TableModel modelo =tabla.getModel();
        //creo el file
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("Stock_Activos");
        
        //escribo los headers encabesados
        
        XSSFRow filaEncabezado =hoja.createRow(0);
        for(int c = 0; c < modelo.getColumnCount(); c++){
            XSSFCell celda = filaEncabezado.createCell(c);
            celda.setCellValue(modelo.getColumnName(c));
            
        }
        
        //escribo los datos
        
        for (int r =0; r<modelo.getRowCount(); r++){
            XSSFRow fila = hoja.createRow(r+1);
            
            for(int c =0; c< modelo.getColumnCount(); c++){
                XSSFCell celda = fila.createCell(c);
                
                Object valor =modelo.getValueAt(r, c);
                
                if(valor!=null){
                    celda.setCellValue(valor.toString());
                }
            }
        }
         //escribo el archivo en disk
        try(FileOutputStream archivoSalida = new FileOutputStream(archivoDestino)){
            libro.write(archivoSalida);
        }finally{
            libro.close(); //lib rec
        }
        
    }
    
}
