package logica;

import interfaces.ServicioAdicional;
import modelo.Boleta;
import modelo.Cliente;
import modelo.Reserva;
import modelo.Habitacion;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.awt.Desktop;
import javax.swing.JOptionPane;

public class GeneradorHtmlBoleta {

    public static void generarFactura(Boleta boleta) {
        try {

            String template = new String(Files.readAllBytes(Paths.get("src/main/java/salidaBoleta/boletaPrueba.html")));

            Cliente cliente = boleta.getCliente();
            Reserva reserva = boleta.getReserva();
            Habitacion habitacion = reserva.getHabitacion();

            double subtotal = reserva.calcularImporteTotal();
            double igv = subtotal * 0.18;
            double total = subtotal + igv;
            
            StringBuilder serviciosHtml = new StringBuilder();
            for (ServicioAdicional servicio : reserva.getServiciosAdicionales()) {
                serviciosHtml.append("<tr>")
                             .append("<td>").append(servicio.getNombre()).append("</td>")
                             .append("<td>S/ ").append(String.format("%.2f", servicio.getPrecio())).append("</td>")
                             .append("</tr>");
            }

            String htmlFinal = template
                .replace("{{fechaBoleta}}", boleta.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .replace("{{clienteNombre}}", cliente.getNombre())
                .replace("{{clienteDni}}", boleta.getCliente().getDni())
                .replace("{{numeroHabitacion}}", String.valueOf(habitacion.getNumero()))
                .replace("{{tipoHabitacion}}", habitacion.getTipo())
                .replace("{{cantNoches}}", String.valueOf(reserva.getDias()))
                .replace("{{subTotal}}", String.format("%.2f", subtotal))
                .replace("{{igv}}", String.format("%.2f", igv))
                .replace("{{importeTotal}}", String.format("%.2f", total))
                .replace("{{serviciosAdicionales}}", serviciosHtml.toString());
                

            String nombreArchivo = "boleta_" + cliente.getNombre().replace(" ", "_") + ".html";
            File archivoBoleta = new File(nombreArchivo);

            try (FileWriter writer = new FileWriter(archivoBoleta)) {
                writer.write(htmlFinal);
                System.out.println("Boleta generada y guardada en: " + archivoBoleta.getAbsolutePath());
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(archivoBoleta.toURI());
            } else {
                JOptionPane.showMessageDialog(null, 
                    "La boleta se guardó en:\n" + archivoBoleta.getAbsolutePath() + "\nPor favor, ábrela manualmente.",
                    "Boleta Guardada",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar la boleta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}