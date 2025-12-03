/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.servicio;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.jfree.chart.JFreeChart;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorChartPDF {

    public static void guardarGraficoComoPDF(JFreeChart chart, String rutaArchivo, int ancho, int alto) {
        
        // 1. Configuración del documento (A4 es estándar para reportes, o personalizado)
        // Usamos un margen estándar para que se vea limpio.
        Document document = new Document(new Rectangle(ancho + 100, alto + 200), 50, 50, 3, 5);

        File archivoSalida = new File(rutaArchivo);
        File directorio = archivoSalida.getParentFile();
        
        try {
            // Validar/Crear directorio
            if (directorio != null && !directorio.exists()) {
                directorio.mkdirs();
            }
            
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivoSalida));
            document.open();

            // ==========================================
            // DEFINICIÓN DE FUENTES (ESTILOS)
            // ==========================================
            Font fontEmpresa = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, BaseColor.GRAY);
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD, BaseColor.BLACK);
            Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, BaseColor.DARK_GRAY);
            Font fontCuerpo = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
            Font fontPie = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.GRAY);

            // ==========================================
            // SECCIÓN 1: ENCABEZADO / MEMBRETE
            // ==========================================
            Paragraph header = new Paragraph("HOTELES ECLIPSE S.A", fontEmpresa);
            header.setAlignment(Element.ALIGN_RIGHT);
            document.add(header);
            
            // Espacio
            document.add(new Paragraph(" ")); 

            // ==========================================
            // SECCIÓN 2: TÍTULO DEL REPORTE
            // ==========================================
            Paragraph titulo = new Paragraph("REPORTE DE ANÁLISIS DE DATOS", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            
            // Fecha dinámica
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fechaActual = sdf.format(new Date());
            Paragraph fecha = new Paragraph("Generado el: " + fechaActual, fontSubtitulo);
            fecha.setAlignment(Element.ALIGN_CENTER);
            document.add(fecha);

            // Línea separadora
            LineSeparator separator = new LineSeparator();
            separator.setLineColor(BaseColor.LIGHT_GRAY);
            document.add(new Chunk(separator));
            document.add(new Paragraph(" ")); // Salto de línea

            // ==========================================
            // SECCIÓN 3: CONTEXTO (TEXTO INTRODUCTORIO)
            // ==========================================
            Paragraph intro = new Paragraph(
                "A continuación se presenta el desglose gráfico correspondiente a los indicadores seleccionados en el sistema. " +
                "Este reporte tiene como finalidad asistir a la gerencia en la toma de decisiones basada en el rendimiento actual.", 
                fontCuerpo
            );
            intro.setAlignment(Element.ALIGN_JUSTIFIED);
            intro.setSpacingAfter(12); // Espacio antes del gráfico
            document.add(intro);

            // ==========================================
            // SECCIÓN 4: INCRUSTAR EL GRÁFICO (COMO IMAGEN)
            // ==========================================
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(ancho, alto);
            Graphics2D graphics2d = template.createGraphics(ancho, alto);
            Rectangle2D chartArea = new Rectangle2D.Double(0, 0, ancho, alto);

            // Dibujar el gráfico en la plantilla
            chart.draw(graphics2d, chartArea);
            graphics2d.dispose();

            // Convertir la plantilla en una Imagen iText para que fluya con el texto
            Image chartImage = Image.getInstance(template);
            chartImage.setAlignment(Element.ALIGN_CENTER);
            document.add(chartImage);

            // ==========================================
            // SECCIÓN 5: PIE DE PÁGINA / NOTA FINAL
            // ==========================================
            document.add(new Paragraph(" ")); // Espacio
            Paragraph conclusion = new Paragraph("Nota: La información contenida en este documento es confidencial y para uso interno exclusivo.", fontPie);
            conclusion.setAlignment(Element.ALIGN_CENTER);
            document.add(conclusion);

        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
            
            // ABRIR AUTOMÁTICAMENTE
            if (archivoSalida.exists()) {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(archivoSalida);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}