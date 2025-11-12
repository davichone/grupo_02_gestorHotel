package modelo.entidades;

/**
 *
 * @author Franz
 */
public interface ServicioAdicional {
    double PRECIO_SAUNA = 30.0;
    double PRECIO_ESTACIONAMIENTO = 20.0;
    double PRECIO_AGUA_CALIENTE = 10.0;
    
    String getNombre();
    double getPrecio();
}
