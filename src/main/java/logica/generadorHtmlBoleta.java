package logica;

import logica.Boleta;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
public class generadorHtmlBoleta {
    
    public static void generarFactura(Boleta boleta) {
        try {
            // Lee el contenido de la plantilla HTML
            String template = new String(Files.readAllBytes(Paths.get("src/main/java/salidaBoleta/templateBoleta.html")));//
           
//            StringBuilder productosHtml = new StringBuilder();
////            for (String producto : boleta.getCliente().getProductosComprados()) {
////                productosHtml.append("<p>- " + producto + "</p>");
////            }

            // Reemplaza los marcadores de posición con los datos reales
            String htmlFinal = template
                .replace("{{fechaBoleta}}", boleta.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .replace("{{clienteNombre}}", boleta.getCliente().getNombre())
                .replace("{{clienteEmail}}", boleta.getCliente().getEmail())
                .replace("{{habitacionesReservadas}}", boleta.getReserva().getRoomType());
              //  .replace("{{productosList}}", productosHtml.toString())
                //.replace("{{totalPagar}}", String.format("%.2f", boleta.getTotalPagar()));

            // html final a nuevo archivo
            String nombreArchivo = "factura_" + boleta.getCliente().getNombre() + ".html";
            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                writer.write(htmlFinal);
                System.out.println("Factura generada y guardada como " + nombreArchivo);
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new File(nombreArchivo).toURI());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
