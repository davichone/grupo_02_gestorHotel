package logica;

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

            String template = new String(Files.readAllBytes(Paths.get("src/main/java/salidaBoleta/templateBoleta.html")));

            Cliente cliente = boleta.getCliente();
            Reserva reserva = boleta.getReserva();
            Habitacion habitacion = reserva.getHabitacion();

            double subtotal = reserva.calcularImporteTotal();
            double igv = subtotal * 0.18;
            double total = subtotal + igv;

            String htmlFinal = template
                .replace("{{fechaBoleta}}", boleta.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .replace("{{clienteNombre}}", cliente.getNombre())
                .replace("{{clienteDni}}", cliente.getDni())
                .replace("{{numeroHabitacion}}", String.valueOf(habitacion.getNumero()))
                .replace("{{tipoHabitacion}}", habitacion.getTipo())
                .replace("{{numeroNoches}}", String.valueOf(reserva.getDias()))
                .replace("{{subtotal}}", String.format("%.2f", subtotal))
                .replace("{{igv}}", String.format("%.2f", igv))
                .replace("{{importeTotal}}", String.format("%.2f", total));

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
            JOptionPane.showMessageDialog(null, 
                "Error al leer la plantilla HTML. Asegúrate de que el archivo exista en la ruta correcta.\n" + e.getMessage(),
                "Error de Archivo",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}