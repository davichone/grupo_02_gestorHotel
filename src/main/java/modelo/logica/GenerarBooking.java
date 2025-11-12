package modelo.logica;

import modelo.dto.ReservaDTO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Desktop;
import javax.swing.JOptionPane;

public class GenerarBooking {

    public static void generarReporte(ArrayList<ReservaDTO> reservas) {
        try {
            String template = new String(Files.readAllBytes(Paths.get("src/main/java/vista/templates/Booking.html")));

            StringBuilder filasClientes = new StringBuilder();
            for (ReservaDTO reserva : reservas) {
                filasClientes.append("<tr>")
                             .append("<td>").append(reserva.getCliente().getNombre()).append("</td>")
                             .append("<td>").append(reserva.getCliente().getDni()).append("</td>")
                             .append("<td>").append(reserva.getHabitacion().getNumero()).append("</td>")
                             .append("<td>").append(reserva.getDias()).append("</td>")
                             .append("<td>S/ ").append(String.format("%.2f", reserva.calcularImporteTotal())).append("</td>")
                             .append("<td></td>")
                             .append("</tr>");
            }

            String htmlFinal = template.replace("{{listaClientes}}", filasClientes.toString());

            String nombreArchivo = "reporte_clientes.html";
            File archivoReporte = new File(nombreArchivo);

            try (FileWriter writer = new FileWriter(archivoReporte)) {
                writer.write(htmlFinal);
                System.out.println("Reporte generado y guardado en: " + archivoReporte.getAbsolutePath());
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(archivoReporte.toURI());
            } else {
                JOptionPane.showMessageDialog(null, 
                    "El reporte se guardó en:\n" + archivoReporte.getAbsolutePath() + "\nPor favor, ábrelo manually.",
                    "Reporte Guardado",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
