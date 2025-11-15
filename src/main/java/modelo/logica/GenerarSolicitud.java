
package modelo.logica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import modelo.dto.SolicitudDTO;


/**
 *
 * @author drola
 */
public class GenerarSolicitud {
    public static void generarSolicitud(SolicitudDTO nuevaSolicitud){
        try {

            String template = new String(Files.readAllBytes(Paths.get("src/main/java/vista/templates/SolicitudActivos.html")));
               
    
            // metodo para transferir atributos a etiquetas del template
            String htmlFinal = template
                .replace("{{id_solicitud}}", nuevaSolicitud.getNroSolicitud())
                .replace("{{fecha_solicitud}}", nuevaSolicitud.getFechaEmision())
                .replace("{{nombre_solicitante}}", nuevaSolicitud.getSolicitante())
                .replace("{{id_operario}}", "0023")
                .replace("{{idHotel}}", nuevaSolicitud.getIdHotel())
                .replace("{{fecha_limite}}", nuevaSolicitud.getGradoUrgencia())
                .replace("{{gradoUrgencia}}", nuevaSolicitud.getGradoUrgencia())
                .replace("{{detalles}}", nuevaSolicitud.getDetalles())
                .replace("{{justificacion}}", nuevaSolicitud.getJustificacion());
                

            String nombreArchivo = "Solicitud_" + nuevaSolicitud.getSolicitante().replace(" ", "_") + ".html";
            File archivoSolicitud = new File(nombreArchivo);

            try (FileWriter writer = new FileWriter(archivoSolicitud)) {
                writer.write(htmlFinal);
                System.out.println("Solicitud generada y guardada en: " + archivoSolicitud.getAbsolutePath());
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(archivoSolicitud.toURI());
            } else {
                JOptionPane.showMessageDialog(null, 
                    "La Solicitud se guardó en:\n" + archivoSolicitud.getAbsolutePath() ,
                    "Solicitud Guardada",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar la solicitud: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
